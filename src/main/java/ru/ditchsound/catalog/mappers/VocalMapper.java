package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.Vocal.VocalDto;
import ru.ditchsound.catalog.model.Vocal;

@Mapper(componentModel = "spring")
public interface VocalMapper {

    VocalDto toDto(Vocal vocal);
    Vocal toEntity(VocalDto vocalDto);
}
