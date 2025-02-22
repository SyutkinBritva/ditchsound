package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ditchsound.catalog.dto.Drums.DrumsDto;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Studio.StudioDto;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.model.Studio;

@Mapper(componentModel = "spring")
public interface DrumsMapper {

    @Mapping(target = "studioDto", source = "drums.studio")
    DrumsDto toDto(Drums drums);

    Drums toEntity(DrumsDto drumsDto);

    Release toReleaseEntity(ReleaseDto releaseDto);

    Studio toStudioEntity(StudioDto studioDto);

    StudioDto toStudioDto (Studio studio);
}
