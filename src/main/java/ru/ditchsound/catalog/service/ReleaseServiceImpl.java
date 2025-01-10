package ru.ditchsound.catalog.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.converters.ReleaseConverter;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.dto.createDTO.ReleaseCreateDto;
import ru.ditchsound.catalog.mappers.ReleaseMapper;
import ru.ditchsound.catalog.mappers.createMappers.CreateReleaseMapper;
import ru.ditchsound.catalog.model.GenreEnum;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.repository.ReleaseRepository;

import java.util.List;
import java.util.stream.Collectors;

///

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final ReleaseMapper releaseMapper;
    private final CreateReleaseMapper createReleaseMapper;
    private final ReleaseConverter releaseConverter;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseMapper releaseMapper, CreateReleaseMapper createReleaseMapper, ReleaseConverter releaseConverter) {
        this.releaseRepository = releaseRepository;
        this.releaseMapper = releaseMapper;
        this.createReleaseMapper = createReleaseMapper;
        this.releaseConverter = releaseConverter;
    }


    @Transactional(readOnly = true)
    public ReleaseDto findById(Long id) {
        Release release = releaseRepository.findById(id).orElseThrow(
               () -> new RuntimeException(String.format("в базе нету релиза с переданным id %s", id)));

        return releaseMapper.toDto(release);
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository.findAll(pageable);
        return releaseList.stream()
                .map(releaseMapper::toDto).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findReleaseByBandName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository
                .findAllByBandNameIgnoreCase(name, pageable);
        return releaseList.stream().
                map(releaseMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releaseList = releaseRepository
                .findAllByReleaseStatus(status, pageable);
        return releaseList.stream().
                map(releaseMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByLabelName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releasePage = releaseRepository.
                findAllByMusicLabel(name, pageable);
        return releasePage.stream().map(releaseMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByGenre (GenreEnum genre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releasePage = releaseRepository.findAllByGenre(genre, pageable);
        return releasePage.stream().map(releaseMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByPrice(Double price, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Release> releasePage = releaseRepository.findAllByPriceTotalAmount(price, pageable);
        return releasePage.stream().map(releaseMapper::toDto).
                collect(Collectors.toList());
    }


    @Transactional()
    public Release createRelease (ReleaseCreateDto releaseCreateDto) {
        Release release = createReleaseMapper.toEntity(releaseCreateDto);
       //Release release = releaseConverter.toEntity(releaseCreateDto);
        return releaseRepository.save(release);
    }

}
