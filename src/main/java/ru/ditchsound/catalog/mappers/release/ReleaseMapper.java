package ru.ditchsound.catalog.mappers.release;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ditchsound.catalog.dto.Drums.DrumsDto;
import ru.ditchsound.catalog.dto.Guitar.GuitarDto;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Release.ReleaseResultDto;
import ru.ditchsound.catalog.dto.Release.ReleaseUpdateDto;
import ru.ditchsound.catalog.dto.Vocal.VocalDto;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.model.Guitar;
import ru.ditchsound.catalog.model.Instrument;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.model.Vocal;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {

    ReleaseDto toDto(Release release);
    Release toEntity(ReleaseDto releaseDto);

    Release toEntity(ReleaseUpdateDto releaseUpdateDto);
    ReleaseUpdateDto toUpdateDto(Release release);

    ReleaseResultDto toResultReleaseDto(Release release);
    Release toReleaseEntity(ReleaseResultDto releaseResultDto);

    @Mapping(source = "studioDto", target = "studio")
    Drums drumsDtoToEntity(DrumsDto drumsDto);
    DrumsDto drumsToDto(Drums drums);

    @Mapping(source = "studioDto", target = "studio")
    Guitar guitarDtoToEntity(GuitarDto guitarDto);
    GuitarDto guitarToDto(Guitar guitar);

    @Mapping(source = "studioDto", target = "studio")
    Instrument instrumentDtoToEntity(InstrumentDto instrumentDto);
    InstrumentDto instrumentToDto(Instrument instrument);

    @Mapping(source = "studioDto", target = "studio")
    Vocal vocalDtoToEntity(VocalDto vocalDto);
    VocalDto vocalToDto(Vocal vocal);

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

    default List<Guitar> listGuitarDTOsToEntities(List<GuitarDto> guitarDTOs){
        if(guitarDTOs == null){
            return null;
        }
        return guitarDTOs.stream().map(this::guitarDtoToEntity).collect(Collectors.toList());
    }

    default List<GuitarDto> listGuitarsToDTOs(List<Guitar> guitars){
        if (guitars == null){
            return null;
        }
        return guitars.stream().map(this::guitarToDto).collect(Collectors.toList());
    }

    default List<Instrument> listInstrumentDTOsToEntities(List<InstrumentDto> instrumentDTOs){
        if(instrumentDTOs == null){
            return null;
        }
        return instrumentDTOs.stream().map(this::instrumentDtoToEntity).collect(Collectors.toList());
    }

    default List<InstrumentDto> listInstrumentsToDTOs(List<Instrument> instrumentList){
        if (instrumentList == null){
            return null;
        }
        return instrumentList.stream().map(this::instrumentToDto).collect(Collectors.toList());
    }

    default List<Vocal> listVocalDTOsToEntities(List<VocalDto> vocalDTOs){
        if (vocalDTOs == null){
            return null;
        }
        return vocalDTOs.stream().map(this::vocalDtoToEntity).collect(Collectors.toList());
    }

    default List<VocalDto> listVocalsToDTOs(List<Vocal> vocalList){
        if(vocalList == null){
            return null;
        }
        return vocalList.stream().map(this::vocalToDto).collect(Collectors.toList());
    }

}
