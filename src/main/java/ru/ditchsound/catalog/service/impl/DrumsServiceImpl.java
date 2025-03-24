package ru.ditchsound.catalog.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.mappers.instruments.InstrumentsMapper;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.repository.DrumsRepository;
import ru.ditchsound.catalog.service.DrumsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DrumsServiceImpl implements DrumsService {

    private final DrumsRepository drumsRepository;
    private final InstrumentsMapper instrumentsMapper;

    @Transactional(readOnly = true)
    @Override
    public InstrumentDto getDrumsByDrumMics(String drumsMics) {
        Optional<Drums> entity = drumsRepository.findFirstByDrumsMics(drumsMics);
        return instrumentsMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstrumentDto> getAllDrumsByDrumMics(String drumsMics, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        List<Drums> drumsList = drumsRepository.findAllByDrumsMics(drumsMics, pageable);

        return drumsList.stream().map(instrumentsMapper::toDto).collect(Collectors.toList());
    }
}
