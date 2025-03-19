package ru.ditchsound.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Guitar;

public interface GuitarRepository extends JpaRepository<Guitar, Long> {
}
