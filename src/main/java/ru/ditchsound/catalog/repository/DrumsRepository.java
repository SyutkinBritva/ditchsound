package ru.ditchsound.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Drums;

import java.util.List;

public interface DrumsRepository extends JpaRepository<Drums, Long> {
//    List<Drums> findAllByStudioStudioNameIgnoreCase(String name);
    List<Drums> findAllByReleaseBandNameIgnoreCase(String name);
    List<Drums> findAllByDrumsType(String type);

}
