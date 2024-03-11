package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.DrumsDto;
import ru.ditchsound.catalog.model.Drums;

@Component
public class DrumConverter {
    //
    public DrumsDto toDrumDto(Drums drums) {
        DrumsDto drumsDto = new DrumsDto();
        //ReleaseDto releaseDto = new ReleaseDto();

        return drumsDto.setDrumsType(drums.getDrumsType())
                .setDrumsModel(drums.getDrumsModel());
    }
}

