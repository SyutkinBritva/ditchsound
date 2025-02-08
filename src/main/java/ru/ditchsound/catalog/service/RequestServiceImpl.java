package ru.ditchsound.catalog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Request.RequestApprovedDto;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.enums.RequestStatus;
import ru.ditchsound.catalog.mappers.PriceMapper;
import ru.ditchsound.catalog.mappers.RequestMapper;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.repository.RequestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final PriceService priceService;
    private final PriceMapper priceMapper;

    @Override
    @Transactional(readOnly = true)
    public RequestDto findRequest(Long id) {

        Request savedRequest = requestRepository
                .getReferenceById(id);
        return requestMapper.toDto(savedRequest);
    }

    @Transactional(readOnly = true)
    public List<RequestDto> findAllRequests(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Request> savedRequests = requestRepository.findAll(pageable);
        return savedRequests.stream()
                .map(requestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RequestDto createRequest(RequestDto requestDto) {
        if(requestDto == null) {
            throw new IllegalArgumentException("Заявка пришла пустой");
        }
        Request request = requestMapper.toEntity(requestDto);
        request.setRequestStatus(RequestStatus.CREATED);
        Request savedRequest = requestRepository.save(request);
        return requestMapper.toDto(savedRequest);
    }

    @Override
    public RequestApprovedDto approveRequest(Long requestId) {

        Request entity = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Заявка не была найдена"));

        if(!entity.getRequestStatus().equals(RequestStatus.CREATED)){
            throw new RuntimeException("Статус заявки не соответствует условиям");
        }

        Double totalAmount = priceService.getTotalAmount(entity.getWorkDescription());

        Price price = priceService.createPriceFromWorkDescription(entity);

        entity.setRequestStatus(RequestStatus.APPROVED);
        entity.setTotalAmount(totalAmount);
        requestRepository.save(entity);

        return requestMapper.toApprovedDto(entity, totalAmount);

    }
}
