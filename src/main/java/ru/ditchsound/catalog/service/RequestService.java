package ru.ditchsound.catalog.service;


import ru.ditchsound.catalog.dto.Request.RequestApprovedDto;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.dto.Request.RequestStatusUpdateDto;

import java.util.List;

public interface RequestService {

    RequestDto findRequest(Long id);

    List<RequestDto> findAllRequests(int page, int size);

    RequestDto createRequest(RequestDto requestDto);

    RequestApprovedDto approveRequest(Long requestId, Double discount);

    RequestStatusUpdateDto confirmPrice(Long requestId, String email);

    RequestStatusUpdateDto declineRequest(Long requestId);

    RequestStatusUpdateDto completeRequest(Long requestId);
}
