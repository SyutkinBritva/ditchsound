package ru.ditchsound.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {

}
