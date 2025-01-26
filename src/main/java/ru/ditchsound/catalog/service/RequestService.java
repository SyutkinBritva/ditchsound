package ru.ditchsound.catalog.service;


import ru.ditchsound.catalog.dto.Request.RequestDto;

public interface RequestService {

    RequestDto findRequest(Long id);

    RequestDto createRequest (RequestDto requestDto);
}
