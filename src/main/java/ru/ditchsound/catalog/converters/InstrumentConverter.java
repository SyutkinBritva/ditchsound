package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.InstrumentDto;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.dto.StudioDto;
import ru.ditchsound.catalog.model.Instrument;

@Component
public class InstrumentConverter {
    public InstrumentDto toInstrumentDto (Instrument instrument){
        InstrumentDto instrumentDto = new InstrumentDto();
        ReleaseDto releaseDto = new ReleaseDto();
        StudioDto studioDto = new StudioDto();

        return instrumentDto.setInstrumentType(instrument.getInstrumentType()).
                setReleaseDto(releaseDto).
                setStudioDto(studioDto);
    }
}
