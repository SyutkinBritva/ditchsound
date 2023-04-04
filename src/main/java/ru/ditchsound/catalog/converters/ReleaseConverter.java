package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.model.Release;

@Component
public class ReleaseConverter {

    public ReleaseDto toReleaseDto(Release release) {
        ReleaseDto releaseDto = new ReleaseDto();

        return releaseDto.setBandName(release.getBandName())
                .setReleaseStatus(release.getReleaseStatus())
                .setWorkDescription(release.getWorkDescription());
    }
}
