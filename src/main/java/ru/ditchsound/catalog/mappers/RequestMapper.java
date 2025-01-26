package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.model.Request;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    Request toEntity(RequestDto requestDto);

    RequestDto toDto(Request request);
}
