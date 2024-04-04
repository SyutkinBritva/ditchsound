package ru.ditchsound.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.converters.VocalConverter;
import ru.ditchsound.catalog.dto.VocalDto;
import ru.ditchsound.catalog.model.Vocal;
import ru.ditchsound.catalog.repository.VocalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VocalServiceImpl implements VocalService {
    private final VocalRepository vocalRepository;
    private final VocalConverter vocalConverter;

    public VocalServiceImpl(VocalRepository vocalRepository, VocalConverter vocalConverter) {
        this.vocalRepository = vocalRepository;
        this.vocalConverter = vocalConverter;
    }


    @Transactional(readOnly = true)
    public VocalDto findById(long id) {
        Vocal vocal = vocalRepository.findById(id).
                orElseThrow(
                        () -> new RuntimeException(String.
                                format("в базе нет барабанов с переданным id %s", id)));
        return vocalConverter.toVocalDto(vocal);
    }

    @Transactional(readOnly = true)
    public List<VocalDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Vocal> vocals = vocalRepository.findAll(pageable);
        return vocals.stream().
                    map(vocalConverter::toVocalDto).
                    collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VocalDto> findAllByStudioName(String studioName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Vocal> vocals = vocalRepository.findAllByStudioStudioName(studioName, pageable);
        return vocals.stream().
                map(vocalConverter::toVocalDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VocalDto> findAllByArtistName(String artistName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Vocal> vocals = vocalRepository.findAllByReleaseBandName(artistName, pageable);
        return vocals.stream().
                map(vocalConverter::toVocalDto).
                collect(Collectors.toList());
    }

}
