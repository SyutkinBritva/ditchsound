package ru.ditchsound.catalog.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ditchsound.catalog.dto.GuitarDto;
import ru.ditchsound.catalog.model.Guitar;

@Mapper(componentModel = "spring",
        uses = {ReleaseMapper.class, StudioMapper.class})
public interface GuitarMapper {


    @Mapping(target = "studioDto", source = "studio")
    @Mapping(target = "releaseDto", source = "release")
    GuitarDto toDto (Guitar guitar);
    @InheritInverseConfiguration
    Guitar toEntity (GuitarDto guitarDto);
}
