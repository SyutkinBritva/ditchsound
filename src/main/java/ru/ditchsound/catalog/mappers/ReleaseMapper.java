package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.PriceDto;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Release;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {


    ReleaseDto toDto (Release release);


    Release toEntity (ReleaseDto releaseDto);

    Price toEntity (PriceDto priceDto);
}