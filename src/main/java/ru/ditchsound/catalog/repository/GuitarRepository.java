package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Guitar;

public interface GuitarRepository extends JpaRepository<Guitar, Long> {
    Page<Guitar> findAllByStudioStudioNameIgnoreCase(String name, Pageable pageable);
    Page<Guitar> findAllByReleaseBandNameIgnoreCase(String name, Pageable pageable);
    Page<Guitar> findAllByGuitarType (String type, Pageable pageable);

}
