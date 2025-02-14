package ru.ditchsound.catalog.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Drums.DrumsDto;
import ru.ditchsound.catalog.dto.Studio.StudioDto;
import ru.ditchsound.catalog.mappers.DrumsMapper;
import ru.ditchsound.catalog.mappers.ReleaseMapper;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.model.Studio;
import ru.ditchsound.catalog.repository.DrumsRepository;
import ru.ditchsound.catalog.repository.ReleaseRepository;
import ru.ditchsound.catalog.repository.StudioRepository;
import ru.ditchsound.catalog.service.DrumsService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrumsServiceImpl implements DrumsService {

    private final DrumsRepository drumsRepository;
    private final ReleaseRepository releaseRepository;
    private final StudioRepository studioRepository;
    private final DrumsMapper drumsMapper;
    private final ReleaseMapper releaseMapper;

    public DrumsServiceImpl(DrumsRepository drumsRepository, ReleaseRepository releaseRepository, StudioRepository studioRepository, DrumsMapper drumsMapper, ReleaseMapper releaseMapper) {
        this.drumsRepository = drumsRepository;
        this.releaseRepository = releaseRepository;
        this.studioRepository = studioRepository;
        this.drumsMapper = drumsMapper;
        this.releaseMapper = releaseMapper;
    }

    @Transactional(readOnly = true)
    public DrumsDto findDrumsById(Long id) {
        Drums drums = drumsRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("в базе нет барабанов с переданным id %s", id)));
        return drumsMapper.toDto(drums);
    }

    @Transactional(readOnly = true)
    public List<DrumsDto> findAllDrums(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Drums> drums = drumsRepository.findAll(pageable);
        return drums.stream().
                map(drumsMapper::toDto).
                collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<DrumsDto> findDrumsByStudio(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Drums> drums = drumsRepository.
                findAllByStudioStudioNameIgnoreCase(name, pageable);
        return drums.stream().
                map(drumsMapper::toDto).
                collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<DrumsDto> findDrumsByBandName(String bandName, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Drums> drums = drumsRepository.
                findAllByReleaseBandNameIgnoreCase(bandName, pageable);
        return drums.stream().
                map(drumsMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DrumsDto> findDrumsByType(String drumType, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Drums> drums = drumsRepository.findAllByDrumsType(drumType, pageable);
        return drums.stream().
                map(drumsMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional
    public DrumsDto createDrums(DrumsDto drumsDto) {

        Drums drums = drumsMapper.toEntity(drumsDto);

        Release release = releaseRepository.findByBandName(drumsDto.getReleaseDto()
                .getBandName()).orElseThrow(() ->
                new EntityNotFoundException("Не найден релиз группы: "
                        + drumsDto.getReleaseDto().getBandName()));

        Studio studio = studioRepository.findStudioByStudioName
                (drumsDto.getStudioDto().getStudioName()).orElseGet(() -> {
            Studio newStudio = drumsMapper.toStudioEntity(drumsDto.getStudioDto());
            return studioRepository.save(newStudio);
        });

        drums.setStudio(studio);
        drums.setRelease(release);
        studio.setDrums(drums);

        release.setDrumsList(drums.getRelease().getDrumsList());

        // Сохраняем инструмент
        Drums savedDrums = drumsRepository.saveAndFlush(drums);
        return drumsMapper.toDto(savedDrums);

    }

    @Override
    public Studio getOrCreateStudio(StudioDto studioDto, Drums drums) {
        if (studioDto == null) {
            throw new IllegalArgumentException("StudioDto не может быть Null");
        }

        if (studioRepository == null) {
            throw new IllegalStateException("StudioRepository не инициализирована");
        }
        return studioRepository.findStudioByStudioName(studioDto.getStudioName())
                .orElseGet(() -> {
                    Studio newStudio = new Studio();
                    newStudio.setStudioName(studioDto.getStudioName());

                    // Устанавливаем связь с Drums
                    newStudio.setDrums(drums);
                    if (drums != null) {
                        drums.setStudio(newStudio);
                    }

                    return studioRepository.save(newStudio);
                });
    }


}

