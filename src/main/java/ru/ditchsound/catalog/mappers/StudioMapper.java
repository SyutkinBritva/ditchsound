package ru.ditchsound.catalog.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.StudioDto;
import ru.ditchsound.catalog.model.Studio;

@Mapper(componentModel = "spring")
public interface StudioMapper {

    StudioDto toDto (Studio studio);

    @InheritInverseConfiguration
    Studio toEntity (StudioDto studioDto);

}
