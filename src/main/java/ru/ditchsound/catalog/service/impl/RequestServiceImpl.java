package ru.ditchsound.catalog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Request.RequestApprovedDto;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.dto.Request.RequestStatusUpdateDto;
import ru.ditchsound.catalog.enums.RequestStatus;
import ru.ditchsound.catalog.exception.RequestNotFoundException;
import ru.ditchsound.catalog.mappers.PriceMapper;
import ru.ditchsound.catalog.mappers.request.RequestMapper;
import ru.ditchsound.catalog.mappers.request.RequestToReleaseMapper;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.repository.PriceRepository;
import ru.ditchsound.catalog.repository.ReleaseRepository;
import ru.ditchsound.catalog.repository.RequestRepository;
import ru.ditchsound.catalog.service.PriceService;
import ru.ditchsound.catalog.service.RequestService;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final PriceRepository priceRepository;
    private final PriceService priceService;
    private final PriceMapper priceMapper;
    private final EmailServiceImpl emailServiceImpl;
    private final RequestToReleaseMapper requestToReleaseMapper;
    private final ReleaseRepository releaseRepository;

    @Override
    @Transactional(readOnly = true)
    public RequestDto findRequest(Long id) {

        Request savedRequest = requestRepository
                .findById(id).orElseThrow(()-> new RequestNotFoundException(id, RequestStatus.UNAVAILABLE));
        return requestMapper.toDto(savedRequest);
    }

    @Transactional(readOnly = true)
    public List<RequestDto> findAllRequests(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Request> savedRequests = requestRepository.findAll(pageable);
        return savedRequests.stream()
                .map(requestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RequestDto createRequest(RequestDto requestDto) {
        if (requestDto == null) {
            throw new IllegalArgumentException("Заявка пришла пустой");
        }
        Request request = requestMapper.toEntity(requestDto);
        request.setRequestStatus(RequestStatus.CREATED);
        Request savedRequest = requestRepository.save(request);
        return requestMapper.toDto(savedRequest);
    }

    @Override
    @Transactional
    public RequestApprovedDto approveRequest(Long requestId, Double discount) {

        Request entity = requestRepository
                .findByIdAndRequestStatus(requestId, RequestStatus.CREATED)
                .orElseThrow(() -> new RequestNotFoundException(requestId, RequestStatus.CREATED));

        Price price = priceService.createPriceFromWorkDescription(entity, discount);
        price.setRequest(entity); // Связываем Price с Request
        priceRepository.save(price);

        entity.setPrice(price);
        requestRepository.save(entity);

        Double totalAmount = priceService.getTotalAmount(
                entity.getWorkDescription(),
                entity.getPrice().getDiscount(),
                entity.getCountOfTrack());

        entity.setRequestStatus(RequestStatus.APPROVED);
        entity.setTotalAmount(totalAmount);
        requestRepository.save(entity);

        emailServiceImpl.sendPriceApprovalEmail(entity);

        return requestMapper.toApprovedDto(entity, totalAmount);

    }

    @Override
    @Transactional
    public RequestStatusUpdateDto confirmPrice(Long requestId,String email) {

        Request request = requestRepository.findByIdAndBandEmailAndRequestStatus(requestId, email, RequestStatus.APPROVED)
                .orElseThrow(() -> new RequestNotFoundException(requestId, RequestStatus.APPROVED));
        request.setRequestStatus(RequestStatus.IN_WORK);
        requestRepository.save(request);
        return requestMapper.toStatusUpdateDto(request);
    }

    @Override
    @Transactional
    public RequestStatusUpdateDto declineRequest(Long requestId) {

        Request request = requestRepository
                .findByIdAndRequestStatus(requestId, RequestStatus.CREATED)
                .orElseThrow(() -> new RequestNotFoundException(requestId, RequestStatus.CREATED));

        request.setRequestStatus(RequestStatus.DECLINED);

        requestRepository.save(request);

        emailServiceImpl.sendDeclineEmail(request);

        return requestMapper.toStatusUpdateDto(request);
    }

    @Override
    @Transactional
    public RequestStatusUpdateDto completeRequest(Long requestId) {
        Request request = requestRepository
                .findByIdAndRequestStatus(requestId, RequestStatus.IN_WORK)
                .orElseThrow(() -> new RequestNotFoundException(requestId, RequestStatus.IN_WORK));

        request.setRequestStatus(RequestStatus.COMPLETED);

    // создание релиза на основе выполненной заявки
        Release release = requestToReleaseMapper.requestToRelease(request);
        releaseRepository.save(release);

        request.setRelease(release);
        requestRepository.save(request);

        return requestMapper.toStatusUpdateDto(request);
    }
}
