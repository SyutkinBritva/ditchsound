package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.model.Price;

public interface PriceService {

    Double getTotalAmount(Price price);

    Double getTotalAmountWithDiscount(Price price);

}
