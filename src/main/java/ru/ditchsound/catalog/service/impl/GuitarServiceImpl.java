package ru.ditchsound.catalog.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.mappers.instruments.InstrumentsMapper;
import ru.ditchsound.catalog.model.Guitar;
import ru.ditchsound.catalog.repository.GuitarRepository;
import ru.ditchsound.catalog.service.GuitarService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GuitarServiceImpl implements GuitarService {

    private final GuitarRepository guitarRepository;

    private final InstrumentsMapper instrumentsMapper;

    @Transactional(readOnly = true)
    @Override
    public InstrumentDto getGuitarBySignalChain(String signalChain) {

        Optional<Guitar> entity = guitarRepository.findFirstBySignalChain(signalChain);

        return instrumentsMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstrumentDto> getAllGuitarsBySignalChain(String signalChain, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        List<Guitar> guitars = guitarRepository.findAllBySignalChain(signalChain, pageable);

        return guitars.stream().map(instrumentsMapper::toDto).collect(Collectors.toList());
    }
}
