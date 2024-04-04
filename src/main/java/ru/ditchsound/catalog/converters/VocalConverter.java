package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.dto.StudioDto;
import ru.ditchsound.catalog.dto.VocalDto;
import ru.ditchsound.catalog.model.Vocal;

@Component
public class VocalConverter {
    public VocalDto toVocalDto(Vocal vocal) {
        VocalDto vocalDto = new VocalDto();
        ReleaseDto releaseDto = new ReleaseDto();
        StudioDto studioDto = new StudioDto();

        return  vocalDto.setVocalType(vocal.getVocalType()).
                setReleaseDto(releaseDto).
                setStudioDto(studioDto);
    }
}
