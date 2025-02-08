package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.Guitar.GuitarDto;
import ru.ditchsound.catalog.model.Guitar;

@Mapper(componentModel = "spring")
public interface GuitarMapper {

    GuitarDto toDto(Guitar guitar);
    Guitar toEntity(GuitarDto guitarDto);
}
