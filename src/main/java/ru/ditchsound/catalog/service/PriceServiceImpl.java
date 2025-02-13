package ru.ditchsound.catalog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        for (WorkDescription work : request.getWorkDescription()) {
            switch (work) {
                case MIXING -> price.setMixing(work.getPrice());
                case MASTERING -> price.setMastering(work.getPrice());
                case PRODUCING -> price.setProducing(work.getPrice());
                case EDITING -> price.setEditing(work.getPrice());

            }
        }

        if (discount < 0 || discount > 0.5) {
            throw new RuntimeException("Скидка должна быть в пределах от 0% до 50%");
        }
        price.setDiscount(discount);
        price.setRequest(request); // Привязываем к заявке
        return priceRepository.save(price);
    }

}

