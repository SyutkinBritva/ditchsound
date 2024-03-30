package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Release;


public interface ReleaseRepository extends JpaRepository<Release, Long> {

    Page<Release> findAllByBandNameIgnoreCase(String bandName, Pageable pageable);

    Page<Release> findAllByReleaseStatus(String status,Pageable pageable);

}
