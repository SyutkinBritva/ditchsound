package ru.ditchsound.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.converters.InstrumentConverter;
import ru.ditchsound.catalog.dto.InstrumentDto;
import ru.ditchsound.catalog.mappers.InstrumentMapper;
import ru.ditchsound.catalog.model.Instrument;
import ru.ditchsound.catalog.repository.InstrumentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentServiceImpl implements InstrumentService {
    private final InstrumentRepository instrumentRepository;
    private final InstrumentMapper instrumentMapper;

    public InstrumentServiceImpl(InstrumentRepository instrumentRepository, InstrumentConverter instrumentConverter, InstrumentMapper instrumentMapper) {
        this.instrumentRepository = instrumentRepository;
        this.instrumentMapper = instrumentMapper;
    }


    @Transactional(readOnly = true)
    public InstrumentDto findInstrById(Long id) {
        Instrument instrument = instrumentRepository.findById(id).
                orElseThrow(
                () -> new RuntimeException(String.format("в базе нет барабанов с переданным id %s", id)));
        return instrumentMapper.toDto(instrument);
    }

    @Transactional(readOnly = true)
    public List<InstrumentDto> findAllInstruments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Instrument> instruments = instrumentRepository.findAll(pageable);
        return instruments.stream().
                map(instrumentMapper::toDto).
                collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<InstrumentDto> findInstrByStudio(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Instrument> instruments = instrumentRepository.
                findAllByStudioStudioNameIgnoreCase(name, pageable);
        return instruments.stream().
                map(instrumentMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InstrumentDto> findInstrByBandName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Instrument> instruments = instrumentRepository.
                findAllByReleaseBandNameIgnoreCase(name, pageable);
        return instruments.stream().
                map(instrumentMapper::toDto).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InstrumentDto> findInstrByType(String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Instrument> instruments = instrumentRepository.
                findAllByInstrumentType(type, pageable);
        return instruments.stream().
                map(instrumentMapper::toDto).
                collect(Collectors.toList());
    }
}
