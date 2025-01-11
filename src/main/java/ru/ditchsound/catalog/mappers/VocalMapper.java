package ru.ditchsound.catalog.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ditchsound.catalog.dto.VocalDto;
import ru.ditchsound.catalog.model.Vocal;

@Mapper(componentModel = "spring",
        uses = {StudioMapper.class, ReleaseMapper.class})
public interface VocalMapper {


    @Mapping(target = "studioDto", source = "studio")
    @Mapping(target = "releaseDto", source = "release")
    VocalDto toDto (Vocal vocal);
    @InheritInverseConfiguration
    Vocal toEntity (VocalDto vocalDto);
}
