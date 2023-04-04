package ru.ditchsound.catalog.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.converters.ReleaseConverter;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.model.Release;
import ru.ditchsound.catalog.repository.ReleaseRepository;
///
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final ReleaseConverter releaseConverter;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseConverter releaseConverter) {
        this.releaseRepository = releaseRepository;
        this.releaseConverter = releaseConverter;
    }


    @Transactional(readOnly = true)
    public ReleaseDto findById(Long id) {
        Release release = releaseRepository.findById(id).orElseThrow(
               () -> new RuntimeException(String.format("в базе нету релиза с переданным id %s", id)));
        ReleaseDto releaseDto = releaseConverter.toReleaseDto(release);
        return releaseDto;
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findAll()
    {
        List<Release> releaseList = releaseRepository.findAll();

        return releaseList.stream().
                map(releaseConverter::toReleaseDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findReleaseByBandName(String name) {
        List<Release> releaseList = releaseRepository.findAllByBandNameIgnoreCase(name);
        return releaseList.stream().
                map(releaseConverter::toReleaseDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReleaseDto> findByStatus(String status) {
        List<Release> releaseList = releaseRepository.findAllByReleaseStatus(status);
        return releaseList.stream().
                map(releaseConverter::toReleaseDto).
                collect(Collectors.toList());
    }


}
