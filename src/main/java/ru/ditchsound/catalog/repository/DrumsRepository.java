package ru.ditchsound.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Drums;

public interface DrumsRepository extends JpaRepository<Drums, Long> {

}
