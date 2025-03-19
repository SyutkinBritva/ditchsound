package ru.ditchsound.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Vocal;

public interface VocalRepository extends JpaRepository<Vocal, Long> {

}
