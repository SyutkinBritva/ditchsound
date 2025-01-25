package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.enums.ReleaseStatus;
import ru.ditchsound.catalog.model.Release;


public interface ReleaseRepository extends JpaRepository<Release, Long> {

    Page<Release> findAllByBandNameIgnoreCase(String bandName, Pageable pageable);
//TODO исправить параметр на ENUM
    Page<Release> findAllByReleaseStatus(ReleaseStatus releaseStatus, Pageable pageable);

    Page<Release> findAllByMusicLabel (String musicLabel, Pageable pageable);

    Page<Release>  findAllByGenre (GenreEnum genreEnum, Pageable pageable);

    //Page<Release> findAllByPriceTotalAmount (Double price, Pageable pageable);

}
