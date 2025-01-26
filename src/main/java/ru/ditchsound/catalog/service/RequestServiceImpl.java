package ru.ditchsound.catalog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.mappers.RequestMapper;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.repository.RequestRepository;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Override
    public RequestDto findRequest(Long id) {
        return null;
    }

    @Override
    public RequestDto createRequest(RequestDto requestDto) {
        Request request = requestMapper.toEntity(requestDto);
        Request savedRequest = requestRepository.save(request);
        return requestMapper.toDto(savedRequest);
    }
}
