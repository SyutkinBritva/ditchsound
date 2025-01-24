package ru.ditchsound.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.DrumsDto;
import ru.ditchsound.catalog.mappers.DrumsMapper;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.repository.DrumsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrumsServiceImpl implements DrumsService {

    private final DrumsRepository drumsRepository;
    private final DrumsMapper drumsMapper;

    public DrumsServiceImpl(DrumsRepository drumsRepository, DrumsMapper drumsMapper) {
        this.drumsRepository = drumsRepository;
        this.drumsMapper = drumsMapper;
    }

    @Transactional(readOnly = true)
    public DrumsDto findDrumsById(Long id){
        Drums drums = drumsRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("в базе нет барабанов с переданным id %s", id)));
        return drumsMapper.toDto(drums);
    }

    @Transactional(readOnly = true)
    public List<DrumsDto> findAllDrums(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Drums> drums = drumsRepository.findAll(pageable);
        return drums.stream().
                map(drumsMapper::toDto).
                collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<DrumsDto> findDrumsByStudio (String name, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Drums> drums = drumsRepository.
                findAllByStudioStudioNameIgnoreCase(name, pageable);
        return drums.stream().
                map(drumsMapper::toDto).
                collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<DrumsDto> findDrumsByBandName(String bandName, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Drums> drums = drumsRepository.
                findAllByReleaseBandNameIgnoreCase(bandName, pageable);
        return drums.stream().
                map(drumsMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DrumsDto> findDrumsByType(String drumType, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Drums> drums = drumsRepository.findAllByDrumsType(drumType, pageable);
        return drums.stream().
                map(drumsMapper::toDto).
                collect(Collectors.toList());
    }

    //TODO исправить return type с Entity на DTO

    public Drums createDrums (DrumsDto drumsDto) {
        Drums drums = drumsMapper.toEntity(drumsDto);
        return drumsRepository.saveAndFlush(drums);
    }

}
