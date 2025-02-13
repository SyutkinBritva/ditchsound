package ru.ditchsound.catalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Request.RequestApprovedDto;
import ru.ditchsound.catalog.dto.Request.RequestCompletedDto;
import ru.ditchsound.catalog.dto.Request.RequestConfirmedDto;
import ru.ditchsound.catalog.dto.Request.RequestDeclinedDto;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.enums.RequestStatus;
import ru.ditchsound.catalog.mappers.PriceMapper;
import ru.ditchsound.catalog.mappers.RequestMapper;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.repository.PriceRepository;
import ru.ditchsound.catalog.repository.RequestRepository;
import ru.ditchsound.catalog.service.PriceService;
import ru.ditchsound.catalog.service.RequestService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final PriceRepository priceRepository;
    private final PriceService priceService;
    private final PriceMapper priceMapper;
    private final EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public RequestDto findRequest(Long id) {

        Request savedRequest = requestRepository
                .getReferenceById(id);
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
                .orElseThrow(() -> new RuntimeException("заявка не найдена"));

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

        emailService.sendPriceApprovalEmail(entity);

        return requestMapper.toApprovedDto(entity, totalAmount);

    }

    @Override
    @Transactional
    public RequestConfirmedDto confirmPrice(Long requestId) {

        Request request = requestRepository
                .findByIdAndRequestStatus(requestId, RequestStatus.APPROVED)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        request.setRequestStatus(RequestStatus.IN_WORK);
        requestRepository.save(request);
        return requestMapper.toConfirmedDto(request);
    }

    @Override
    @Transactional
    public RequestDeclinedDto declineRequest(Long requestId) {

        Request request = requestRepository
                .findByIdAndRequestStatus(requestId, RequestStatus.CREATED)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));

        request.setRequestStatus(RequestStatus.DECLINED);

        requestRepository.save(request);

        emailService.sendDeclineEmail(request);

        return requestMapper.toDeclinedDto(request);
    }

    @Override
    @Transactional
    public RequestCompletedDto completeRequest(Long requestId) {
        Request request = requestRepository
                .findByIdAndRequestStatus(requestId, RequestStatus.IN_WORK)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));

        request.setRequestStatus(RequestStatus.COMPLETED);

        requestRepository.save(request);

        return requestMapper.toCompletedDto(request);
    }
}
