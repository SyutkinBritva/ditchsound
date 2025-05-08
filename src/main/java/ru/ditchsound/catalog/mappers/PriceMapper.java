package ru.ditchsound.catalog.mappers;

import org.mapstruct.Mapper;
import ru.ditchsound.catalog.dto.Price.PriceDto;
import ru.ditchsound.catalog.model.Price;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    Price toEntity(PriceDto priceDto);

    PriceDto toDto(Price price);
}
