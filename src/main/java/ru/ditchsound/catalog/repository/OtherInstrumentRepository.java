package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.OtherInstrument;

import java.util.List;
import java.util.Optional;

public interface OtherInstrumentRepository extends JpaRepository<OtherInstrument, Long> {

    Optional<OtherInstrument> findFirstByPreset(String preset);

    List<OtherInstrument> findAllByPreset(String preset, Pageable pageable);
}
