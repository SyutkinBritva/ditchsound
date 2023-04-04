package ru.ditchsound.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Release;

import java.util.List;


public interface ReleaseRepository extends JpaRepository<Release, Long> {

    List<Release> findAllByBandNameIgnoreCase(String bandName);

    List<Release> findAllByReleaseStatus(String status);

}
