package ru.ditchsound.catalog.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Release.ReleaseResultDto;
import ru.ditchsound.catalog.dto.Release.ReleaseUpdateDto;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.exception.ReleaseNotFoundException;
import ru.ditchsound.catalog.mappers.DrumsMapper;
import ru.ditchsound.catalog.mappers.release.ReleaseMapper;
import ru.ditchsound.catalog.mappers.release.ReleaseUpdatedFromReleaseUpdateDto;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.repository.DrumsRepository;
import ru.ditchsound.catalog.repository.ReleaseRepository;
import ru.ditchsound.catalog.repository.RequestRepository;
import ru.ditchsound.catalog.repository.StudioRepository;
import ru.ditchsound.catalog.service.DrumsService;
import ru.ditchsound.catalog.service.ReleaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;

    private final ReleaseMapper releaseMapper;

    private final DrumsService drumsService;

    private final DrumsRepository drumsRepository;

    private final RequestRepository requestRepository;

    private final StudioRepository studioRepository;

    private final DrumsMapper drumsMapper;

    private final ReleaseUpdatedFromReleaseUpdateDto toTransitional;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository,
                              ReleaseMapper releaseMapper,
                              DrumsService drumsService, DrumsRepository drumsRepository, RequestRepository requestRepository, StudioRepository studioRepository, DrumsMapper drumsMapper, ReleaseUpdatedFromReleaseUpdateDto updatedFromReleaseUpdateDto, ReleaseUpdatedFromReleaseUpdateDto toTransitional) {

        this.releaseRepository = releaseRepository;
        this.releaseMapper = releaseMapper;
        this.drumsService = drumsService;
        this.drumsRepository = drumsRepository;
        this.requestRepository = requestRepository;
        this.studioRepository = studioRepository;
        this.drumsMapper = drumsMapper;
        this.toTransitional = toTransitional;
    }

    @Transactional(readOnly = true)
    public ReleaseDto findById(Long id) {

        Release release = releaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("в базе нету релиза с переданным id %s", id)));

        return releaseMapper.toDto(release);
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository.findAll(pageable);
        return releaseList.stream()
                .map(releaseMapper::toDto)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findReleaseByBandName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository
                .findAllByBandNameIgnoreCase(name, pageable);
        return releaseList.stream().
                map(releaseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByLabelName(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releasePage = releaseRepository.findAllByMusicLabel(name, pageable);
        return releasePage.stream()
                .map(releaseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByGenre(GenreEnum genre, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releasePage = releaseRepository.findAllByGenre(genre, pageable);
        return releasePage
                .stream()
                .map(releaseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReleaseResultDto updateRelease(Long id, ReleaseUpdateDto releaseUpdateDto) {

        Release release = releaseRepository.findById(id).orElseThrow(()-> new ReleaseNotFoundException(id));
        Release transitRelease = toTransitional.updateFromReleaseUpdateDto(releaseUpdateDto, release);
        releaseRepository.save(transitRelease);

        return releaseMapper.toResultReleaseDto(transitRelease);
    }
}