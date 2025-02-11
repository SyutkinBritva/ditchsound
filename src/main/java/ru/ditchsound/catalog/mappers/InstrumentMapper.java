package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.model.Instrument;

@Mapper(componentModel = "spring")
public interface InstrumentMapper {

    InstrumentDto toDto(Instrument instrument);
    Instrument toEntity(InstrumentDto instrumentDto);
}
