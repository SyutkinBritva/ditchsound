package ru.ditchsound.catalog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.enums.WorkDescription;
import ru.ditchsound.catalog.model.Price;
import ru.ditchsound.catalog.model.Request;
import ru.ditchsound.catalog.repository.PriceRepository;
import ru.ditchsound.catalog.service.PriceService;

import java.util.Arrays;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    @Transactional
    public Double getTotalAmount(WorkDescription[] workDescriptions,
                                 Double discount,
                                 Integer countOfTrack) {

        double total = Arrays.stream(workDescriptions)
                .mapToDouble(WorkDescription::getPrice)
                .sum();

        return total * (1 - discount) * countOfTrack;
    }

    @Override
    @Transactional
    public Price createPriceFromWorkDescription(Request request, Double discount) {

        Price price = new Price();
        Integer countOfTrack = request.getCountOfTrack();
        for (WorkDescription work : request.getWorkDescription()) {
            switch (work) {
                case MIXING -> price.setMixing(work.getPrice() * countOfTrack);
                case MASTERING -> price.setMastering(work.getPrice() * countOfTrack);
                case PRODUCING -> price.setProducing(work.getPrice() * countOfTrack);
                case EDITING -> price.setEditing(work.getPrice() * countOfTrack);
            }
        }

        if (discount < 0 || discount > 0.5) {
            throw new RuntimeException("Скидка должна быть в пределах от 0% до 50%");
        }
        price.setDiscount(discount);
        price.setNumberOfSongs(request.getCountOfTrack());
        price.setRequest(request); // Привязываем к заявке
        return priceRepository.save(price);
    }

}

