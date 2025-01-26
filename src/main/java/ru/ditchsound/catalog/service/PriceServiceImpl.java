package ru.ditchsound.catalog.service;

import org.springframework.stereotype.Service;
import ru.ditchsound.catalog.model.Price;

@Service
public class PriceServiceImpl implements PriceService{

    @Override
    public Double getTotalAmount(Price price) {

        return (price.getEditingDrums() + price.getMixing()
                + price.getEditingInstrument() + price.getMastering()
                + price.getProducing() + price.getEditingVocals());
    }

    @Override
    public Double getTotalAmountWithDiscount(Price price) {

        return getTotalAmount(price) * (1 - price.getDiscount());

    }
}
