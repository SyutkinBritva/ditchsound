package ru.ditchsound.catalog.service;

import org.springframework.stereotype.Service;
import ru.ditchsound.catalog.enums.WorkDescription;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.repository.PriceRepository;

import java.util.Arrays;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Double getTotalAmount(WorkDescription[] workDescriptions) {
        return Arrays.stream(workDescriptions)
                .mapToDouble(WorkDescription::getPrice)
                .sum();
    }

    @Override
    public Price createPriceFromWorkDescription(Request request) {
        Price price = new Price();
        for (WorkDescription work : request.getWorkDescription()) {
            switch (work) {
                case MIXING -> price.setMixing(work.getPrice());
                case MASTERING -> price.setMastering(work.getPrice());
                case PRODUCING -> price.setProducing(work.getPrice());
                case EDITING -> price.setEditing(work.getPrice());

            }
        }
        price.setRequest(request); // Привязываем к заявке
        return priceRepository.save(price);
    }
}

