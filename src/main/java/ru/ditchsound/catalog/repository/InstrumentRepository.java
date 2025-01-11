package ru.ditchsound.catalog.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Page<Instrument> findAllByStudioStudioNameIgnoreCase(String studioName, Pageable pageable);
    Page<Instrument> findAllByReleaseBandNameIgnoreCase(String bandName , Pageable pageable);
    Page<Instrument> findAllByInstrumentType (String instrType , Pageable pageable);
}
