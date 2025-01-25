package ru.ditchsound.catalog.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.ditchsound.catalog.dto.DrumsDto;
import ru.ditchsound.catalog.model.Drums;

@Mapper(componentModel = "spring", uses = { StudioMapper.class, ReleaseMapper.class })
public interface DrumsMapper {

    @Mapping(target = "studioDto", source = "studio")
    @Mapping(target = "releaseDto", source = "release")
    DrumsDto toDto(Drums drums);

    @InheritInverseConfiguration
    Drums toEntity(DrumsDto drumsDto);
}
