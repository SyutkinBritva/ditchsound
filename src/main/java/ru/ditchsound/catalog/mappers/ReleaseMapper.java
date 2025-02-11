package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ditchsound.catalog.dto.Drums.DrumsDto;
import ru.ditchsound.catalog.dto.Release.ReleaseCreateDto;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.model.Request;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {

    ReleaseDto toDto(Release release);
    Release toEntity(ReleaseDto releaseDto);

    Release toEntity(ReleaseCreateDto releaseCreateDto);
    ReleaseCreateDto toCreateDto(Release release);

    RequestDto requestToDto(Request request);
    Request requestDtoToentity(RequestDto requestDto);

    @Mapping(source = "studioDto", target = "studio")
    Drums drumsDtoToEntity(DrumsDto drumsDto);
    DrumsDto drumsToDto(Drums drums);

    default List<Drums> listDrumsDTOsToEntities(List<DrumsDto> drumsDTOs){
        if(drumsDTOs == null){
            return null;
        }
        return drumsDTOs.stream().map(this::drumsDtoToEntity).collect(Collectors.toList());
    }

    default List<DrumsDto> listDrumsToDTOs (List<Drums> drums){
        if(drums == null){
            return null;
        }
        return drums.stream().map(this::drumsToDto).collect(Collectors.toList());
    }

}
