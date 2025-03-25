package ru.ditchsound.catalog.mappers.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ditchsound.catalog.dto.Request.RequestApprovedDto;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.dto.Request.RequestStatusUpdateDto;
import ru.ditchsound.catalog.model.Request;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    Request toEntity(RequestDto requestDto);

    RequestDto toDto(Request request);

    @Mapping(source = "totalAmount", target = "totalAmount")
    RequestApprovedDto toApprovedDto (Request entity, Double totalAmount);

    RequestStatusUpdateDto toStatusUpdateDto (Request entity);
}
