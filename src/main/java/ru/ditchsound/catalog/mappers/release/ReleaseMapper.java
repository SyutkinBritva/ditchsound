package ru.ditchsound.catalog.mappers.release;

import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Release.ReleaseResultDto;
import ru.ditchsound.catalog.dto.Release.ReleaseUpdateDto;
import ru.ditchsound.catalog.model.Release;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {

    ReleaseDto toDto(Release release);
    Release toEntity(ReleaseDto releaseDto);

    Release toEntity(ReleaseUpdateDto releaseUpdateDto);
    ReleaseUpdateDto toUpdateDto(Release release);

    ReleaseResultDto toResultReleaseDto(Release release);
    Release toReleaseEntity(ReleaseResultDto releaseResultDto);

}
