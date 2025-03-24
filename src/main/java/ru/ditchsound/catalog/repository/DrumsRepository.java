package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Drums;

import java.util.List;
import java.util.Optional;

public interface DrumsRepository extends JpaRepository<Drums, Long> {

    Optional<Drums> findFirstByDrumsMics(String drumsMics);

    List<Drums> findAllByDrumsMics(String drumsMics, Pageable pageable);
}
