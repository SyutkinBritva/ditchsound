package ru.ditchsound.catalog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.repository.DrumsRepository;

import java.util.List;

@Service
public class DrumsServiceImpl implements DrumsService {

    private final DrumsRepository drumsRepository;

    public DrumsServiceImpl(DrumsRepository drumsRepository) {
        this.drumsRepository = drumsRepository;
    }

    @Transactional(readOnly = true)
    public Drums findDrumsById(Long id){
        return drumsRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("в базе нет барабанов с переданным id %s", id)));
    }

    @Transactional(readOnly = true)
    public List<Drums> findAllDrums(){
        return drumsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Drums> findDrumsByStudio(String name){
        return drumsRepository.findAllByStudioStudioNameIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Drums> findDrumsByBandName(String name){
        return drumsRepository.findAllByReleaseBandNameIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Drums> findDrumsByType(String type){
        return drumsRepository.findAllByDrumsType(type);
    }

}
