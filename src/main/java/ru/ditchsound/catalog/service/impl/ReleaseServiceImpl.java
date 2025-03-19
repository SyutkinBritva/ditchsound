package ru.ditchsound.catalog.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Release.ReleaseResultDto;
import ru.ditchsound.catalog.dto.Release.ReleaseUpdateDto;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.exception.ReleaseNotFoundException;
import ru.ditchsound.catalog.mappers.instruments.InstrumentsMapper;
import ru.ditchsound.catalog.mappers.release.ReleaseMapper;
import ru.ditchsound.catalog.mappers.release.ReleaseUpdateConverter;
import ru.ditchsound.catalog.model.Instrument;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.model.Studio;
import ru.ditchsound.catalog.repository.DrumsRepository;
import ru.ditchsound.catalog.repository.InstrumentRepository;
import ru.ditchsound.catalog.repository.ReleaseRepository;
import ru.ditchsound.catalog.repository.RequestRepository;
import ru.ditchsound.catalog.repository.StudioRepository;
import ru.ditchsound.catalog.service.ReleaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final InstrumentRepository instrumentRepository;

    private final ReleaseRepository releaseRepository;

    private final ReleaseMapper releaseMapper;

    private final InstrumentsMapper instrumentsMapper;

    private final DrumsRepository drumsRepository;

    private final RequestRepository requestRepository;

    private final StudioRepository studioRepository;


    private final ReleaseUpdateConverter toTransitional;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository,
                              ReleaseMapper releaseMapper,
                              InstrumentRepository instrumentRepository, InstrumentsMapper instrumentsMapper, DrumsRepository drumsRepository, RequestRepository requestRepository, StudioRepository studioRepository, ReleaseUpdateConverter updatedFromReleaseUpdateDto, ReleaseUpdateConverter toTransitional) {

        this.releaseRepository = releaseRepository;
        this.releaseMapper = releaseMapper;
        this.instrumentRepository = instrumentRepository;
        this.instrumentsMapper = instrumentsMapper;
        this.drumsRepository = drumsRepository;
        this.requestRepository = requestRepository;
        this.studioRepository = studioRepository;
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
    public ReleaseResultDto updateRelease(ReleaseUpdateDto releaseUpdateDto) {

        Release release = releaseRepository.findById(releaseUpdateDto.getId()).orElseThrow(() -> new ReleaseNotFoundException(releaseUpdateDto.getId()));
        Release transitRelease = toTransitional.updateFromReleaseUpdateDto(releaseUpdateDto, release);
        //releaseRepository.save(transitRelease);
        return releaseMapper.toResultReleaseDto(transitRelease);
    }

    @Transactional
    public ReleaseResultDto addInstrumentToRelease(InstrumentDto instrumentDto, String bandName, String releaseName) {

        Release release = releaseRepository.findByBandNameAndReleaseName(bandName, releaseName).orElseThrow(() -> new ReleaseNotFoundException(bandName, releaseName));

        Studio studio = studioRepository.findStudioByStudioName(instrumentDto.getStudioDto().getStudioName())
                .orElseGet(() -> {
                    Studio newStudio = new Studio();
                    newStudio.setStudioName(instrumentDto.getStudioDto().getStudioName());
                    return studioRepository.save(newStudio);
                });

        Instrument instrument = instrumentsMapper.toEntity(instrumentDto, studio);
        instrument.setRelease(release);

        Instrument savedInstrument = instrumentRepository.save(instrument);

        release.getInstrumentList().add(savedInstrument);

        releaseRepository.save(release);
        return releaseMapper.toResultReleaseDto(release);
    }


}