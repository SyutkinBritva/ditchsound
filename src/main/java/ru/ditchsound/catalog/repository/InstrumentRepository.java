package ru.ditchsound.catalog.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.enums.InstrumentTypeEnum;
import ru.ditchsound.catalog.model.Instrument;

import java.util.Optional;


public interface InstrumentRepository extends JpaRepository<Instrument, Long> {


    Page<Instrument> findAllByStudioStudioName(String studioName, Pageable pageable);

    Page<Instrument> findAllByReleaseBandName(String bandName, Pageable pageable);

    Page<Instrument> findAllByInstrumentType(InstrumentTypeEnum instrumentType, Pageable pageable);

    Optional<Instrument> findFirstInstrumentByStudioStudioName(String studioName);

    Optional<Instrument> findFirstInstrumentByReleaseReleaseName(String releaseName);

    Optional<Instrument> findFirstInstrumentByRelease_BandName(String bandName);

    Optional<Instrument> findInstrumentByInstrumentTypeAndReleaseReleaseName(InstrumentTypeEnum instrumentType, String releaseName);

}
