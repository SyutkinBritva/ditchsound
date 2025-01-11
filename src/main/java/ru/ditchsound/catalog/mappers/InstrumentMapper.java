package ru.ditchsound.catalog.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ditchsound.catalog.dto.InstrumentDto;
import ru.ditchsound.catalog.model.Instrument;

@Mapper(componentModel = "spring",
        uses = {ReleaseMapper.class, StudioMapper.class})
public interface InstrumentMapper {


    @Mapping(target = "studioDto", source = "studio")
    @Mapping(target = "releaseDto", source = "release")
    InstrumentDto toDto (Instrument instrument);

    @InheritInverseConfiguration
    Instrument toEntity (InstrumentDto instrumentDto);
}
