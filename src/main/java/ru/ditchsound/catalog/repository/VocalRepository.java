package ru.ditchsound.catalog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Vocal;

import java.util.List;
import java.util.Optional;

public interface VocalRepository extends JpaRepository<Vocal, Long> {

    Optional<Vocal> findFirstByVocalMic(String vocalMic);

    Optional<Vocal> findFirstByVocalTechnique(String vocalTechnique);

    List<Vocal> findAllByVocalMic(String vocalMic, Pageable pageable);

    List<Vocal> findAllByVocalTechnique(String vocalTechnique, Pageable pageable);
}
