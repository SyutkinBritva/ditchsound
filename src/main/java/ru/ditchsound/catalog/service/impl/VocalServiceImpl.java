package ru.ditchsound.catalog.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.mappers.instruments.InstrumentsMapper;
import ru.ditchsound.catalog.model.Vocal;
import ru.ditchsound.catalog.repository.VocalRepository;
import ru.ditchsound.catalog.service.VocalService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VocalServiceImpl implements VocalService {

    private final VocalRepository vocalRepository;

    private final InstrumentsMapper instrumentsMapper;

    @Transactional(readOnly = true)
    @Override
    public InstrumentDto getVocalByVocalMic(String vocalMic) {

        Optional<Vocal> entity = vocalRepository.findFirstByVocalMic(vocalMic);

        return instrumentsMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public InstrumentDto getVocalByVocalTechnique(String vocalTechnique) {

        Optional<Vocal> entity = vocalRepository.findFirstByVocalTechnique(vocalTechnique);

        return instrumentsMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstrumentDto> getAllVocalsByVocalMic(String vocalMic, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        List<Vocal> vocals = vocalRepository.findAllByVocalMic(vocalMic, pageable);

        return vocals.stream().map(instrumentsMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstrumentDto> getAllVocalsByVocalTechnique(String vocalTechnique, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        List<Vocal> vocals = vocalRepository.findAllByVocalTechnique(vocalTechnique, pageable);

        return vocals.stream().map(instrumentsMapper::toDto).collect(Collectors.toList());
    }
}
