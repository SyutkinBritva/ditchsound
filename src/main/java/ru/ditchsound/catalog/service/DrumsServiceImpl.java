package ru.ditchsound.catalog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.converters.DrumConverter;
import ru.ditchsound.catalog.dto.DrumsDto;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.repository.DrumsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrumsServiceImpl implements DrumsService {

    private final DrumsRepository drumsRepository;
    private final DrumConverter drumConverter;

    public DrumsServiceImpl(DrumsRepository drumsRepository, DrumConverter drumConverter) {
        this.drumsRepository = drumsRepository;
        this.drumConverter = drumConverter;
    }

    @Transactional(readOnly = true)
    public DrumsDto findDrumsById(Long id){
        Drums drums = drumsRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("в базе нет барабанов с переданным id %s", id)));
        DrumsDto drumsDto = drumConverter.toDrumDto(drums);
        return drumsDto;
    }
///
    @Transactional(readOnly = true)
    public List<DrumsDto> findAllDrums(){
        List<Drums> drums = drumsRepository.findAll();
        List<DrumsDto> dtos = drums.stream().
                map(d -> drumConverter.toDrumDto(d)).
                collect(Collectors.toList());
        return dtos;
    }

//    @Transactional(readOnly = true)
//    public List<Drums> findDrumsByStudio(String name){
//        return drumsRepository.findAllByStudioStudioNameIgnoreCase(name);
//    }

    @Transactional(readOnly = true)
    public List<DrumsDto> findDrumsByBandName(String name){
        List<Drums> drums = drumsRepository.findAllByReleaseBandNameIgnoreCase(name);
        List<DrumsDto> dtos = drums.stream().
                map(d -> drumConverter.toDrumDto(d)).
                collect(Collectors.toList());
        return dtos;
    }

    @Transactional(readOnly = true)
    public List<DrumsDto> findDrumsByType(String type){
        List<Drums> drums = drumsRepository.findAllByDrumsType(type);
        List<DrumsDto> dtos = drums.stream().
                map(d -> drumConverter.toDrumDto(d)).
                collect(Collectors.toList());
        return dtos;
    }

}
