package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Drums;

public interface DrumsRepository extends JpaRepository<Drums, Long> {
    Page<Drums> findAllByStudioStudioNameIgnoreCase(String studioName, Pageable pageable);
    Page<Drums> findAllByReleaseBandNameIgnoreCase(String bandName , Pageable pageable);
    Page<Drums> findAllByDrumsType(String drumsType , Pageable pageable);
}
