package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.model.Release;

import java.util.Optional;


public interface ReleaseRepository extends JpaRepository<Release, Long> {

    Optional<Release> findByBandName(String bandName);

    Optional<Release> findByIdAndBandNameIgnoreCase(Long id, String bandName);

    Optional<Release> findByBandNameAndReleaseName(String bandName, String releaseName);

    Page<Release> findAllByBandNameIgnoreCase(String bandName, Pageable pageable);

    Page<Release> findAllByMusicLabel(String musicLabel, Pageable pageable);

    Page<Release> findAllByGenre(GenreEnum genre, Pageable pageable);


}
