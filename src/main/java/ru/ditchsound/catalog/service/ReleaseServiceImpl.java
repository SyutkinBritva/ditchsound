package ru.ditchsound.catalog.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.repository.ReleaseRepository;

import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Transactional(readOnly = true)
    public Release findById(Long id) {
       return releaseRepository.findById(id).orElseThrow(
               () -> new RuntimeException(String.format("в базе нету релиза с переданным id %s", id)));
    }

    @Transactional(readOnly = true)
    public List<Release> findAll() {
        return releaseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Release> findReleaseByBandName(String name) {
        return releaseRepository.findAllByBandNameIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Release> findByStatus(String status) {
        return releaseRepository.findAllByReleaseStatus(status);
    }


}
