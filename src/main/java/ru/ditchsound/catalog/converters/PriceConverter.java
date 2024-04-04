package ru.ditchsound.catalog.converters;

import org.springframework.stereotype.Component;
import ru.ditchsound.catalog.dto.PriceDto;
import ru.ditchsound.catalog.model.Price;

@Component
public class PriceConverter {

    public PriceDto toPriceDto(Price price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setTotalAmount(price.getTotalAmount());
        return priceDto;
    }
}
