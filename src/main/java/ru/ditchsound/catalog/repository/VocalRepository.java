package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Vocal;

public interface VocalRepository extends JpaRepository<Vocal, Long> {

    Page<Vocal> findAllByVocalType(String type, Pageable pageable);
    Page<Vocal> findAllByReleaseBandName(String bandName, Pageable pageable);
    Page<Vocal> findAllByStudioStudioName (String name, Pageable pageable);
}
