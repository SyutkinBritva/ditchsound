package ru.ditchsound.catalog.mappers.createMappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ditchsound.catalog.dto.PriceDto;
import ru.ditchsound.catalog.dto.createDTO.ReleaseCreateDto;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Release;

@Mapper(componentModel = "spring")
public interface CreateReleaseMapper {


    ReleaseCreateDto toCreateDto (Release release);

    Release toEntity (ReleaseCreateDto releaseCreateDto);

    @Mapping(target = "totalAmount", source = "releaseCreateDto.priceDto.totalAmount")
    Price relesaseDtoToPrice (ReleaseCreateDto releaseCreateDto);

    Price toEntity(PriceDto priceDto);
}
