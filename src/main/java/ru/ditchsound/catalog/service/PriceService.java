package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.enums.WorkDescription;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Request;

public interface PriceService {

    Double getTotalAmount(WorkDescription[] workDescription);

    Price createPriceFromWorkDescription(Request request);

}
