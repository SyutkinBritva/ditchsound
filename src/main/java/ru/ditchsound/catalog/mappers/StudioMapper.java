package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.Drums.DrumsDto;
import ru.ditchsound.catalog.dto.Studio.StudioDto;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.model.Studio;

@Mapper(componentModel = "spring")
public interface StudioMapper {

    StudioDto toDto (Studio studio);

    Studio toEntity (StudioDto studioDto);

    Drums toDrumsEntity(DrumsDto drumsDto);

    DrumsDto toDrumsDto (Drums drums);

}
