package ru.ditchsound.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Guitar.GuitarDto;
import ru.ditchsound.catalog.mappers.GuitarMapper;
import ru.ditchsound.catalog.model.Guitar;
import ru.ditchsound.catalog.repository.GuitarRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuitarServiceImpl implements GuitarService  {
    private final GuitarRepository guitarRepository;
    private final GuitarMapper guitarMapper;
    public GuitarServiceImpl(GuitarRepository guitarRepository, GuitarMapper guitarMapper) {
        this.guitarRepository = guitarRepository;
        this.guitarMapper = guitarMapper;
    }

    @Transactional(readOnly = true)
    public GuitarDto findById(Long id) {
        Guitar guitar = guitarRepository.findById(id).
                orElseThrow(
                        () -> new RuntimeException
                                (String.format("в базе нет барабанов с переданным id %s", id)));
        return guitarMapper.toDto(guitar);
    }

    @Transactional(readOnly = true)
    public List<GuitarDto> findAllGuitars(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Guitar> guitars = guitarRepository.findAll(pageable);
        return guitars.stream().
                map(guitarMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GuitarDto> findByGuitarType(String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Guitar> guitars = guitarRepository.findAllByGuitarType(type, pageable);
        return guitars.stream().
                map(guitarMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GuitarDto> findByBandName(String bandName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Guitar> guitars = guitarRepository.
                findAllByReleaseBandNameIgnoreCase(bandName, pageable);
        return guitars.stream().
                map(guitarMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GuitarDto> findByStudioName(String studioName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Guitar> guitars = guitarRepository.
                findAllByStudioStudioNameIgnoreCase(studioName, pageable);
        return guitars.stream().
                map(guitarMapper::toDto).
                collect(Collectors.toList());
    }
}
