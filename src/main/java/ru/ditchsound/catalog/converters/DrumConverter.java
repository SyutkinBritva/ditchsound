package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.DrumsDto;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.dto.StudioDto;
import ru.ditchsound.catalog.model.Drums;

@Component
public class DrumConverter {
    public DrumsDto toDrumDto(Drums drums) {
        DrumsDto drumsDto = new DrumsDto();
        ReleaseDto releaseDto = new ReleaseDto();
        StudioDto studioDto = new StudioDto();

        return drumsDto.setDrumsType(drums.getDrumsType())
                .setDrumsModel(drums.getDrumsModel()).
                        setReleaseDto(releaseDto).
                        setStudioDto(studioDto);
    }
}

