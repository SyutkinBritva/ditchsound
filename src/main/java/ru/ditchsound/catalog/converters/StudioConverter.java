package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.StudioDto;
import ru.ditchsound.catalog.model.Studio;

@Component
public class StudioConverter {
    public StudioDto toStudioDto(Studio studio) {
        StudioDto studioDto = new StudioDto();
        return studioDto.setName(studio.getStudioName());
    }
}
