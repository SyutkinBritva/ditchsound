package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.GuitarDto;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.dto.StudioDto;
import ru.ditchsound.catalog.model.Guitar;
@Component
public class GuitarConverter {
    public GuitarDto toGuitarDto(Guitar guitar) {
        GuitarDto guitarDto = new GuitarDto();
        ReleaseDto releaseDto = new ReleaseDto();
        StudioDto studioDto = new StudioDto();

        guitarDto.setGuitarType(guitar.getGuitarType()).
                setReleaseDto(releaseDto).setStudioDto(studioDto);

        return guitarDto;
    }
}
