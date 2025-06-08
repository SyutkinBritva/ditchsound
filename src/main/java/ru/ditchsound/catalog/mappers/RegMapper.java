package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.Security.RegDto;
import ru.ditchsound.catalog.model.User;

@Mapper(componentModel = "spring")
public interface RegMapper {

    User toEntity(RegDto dto);

}
