package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Guitar;

import java.util.List;
import java.util.Optional;

public interface GuitarRepository extends JpaRepository<Guitar, Long> {

    Optional<Guitar> findFirstBySignalChain(String signalChain);

    List<Guitar> findAllBySignalChain(String signalChain, Pageable pageable);
}
