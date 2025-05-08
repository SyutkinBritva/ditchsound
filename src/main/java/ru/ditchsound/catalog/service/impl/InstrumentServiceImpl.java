package ru.ditchsound.catalog.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.enums.InstrumentTypeEnum;
import ru.ditchsound.catalog.mappers.instruments.InstrumentsMapper;
import ru.ditchsound.catalog.model.Instrument;
import ru.ditchsound.catalog.repository.InstrumentRepository;
import ru.ditchsound.catalog.service.InstrumentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstrumentServiceImpl implements InstrumentService {
    private final InstrumentRepository instrumentRepository;
    private final InstrumentsMapper instrumentsMapper;


    @Transactional(readOnly = true)
    @Override
    public InstrumentDto findInstrById(Long id) {

        Optional<Instrument> entity = instrumentRepository.findById(id);

        return instrumentsMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public InstrumentDto findInstrByStudio(String studioName) {

        Optional<Instrument> entity = instrumentRepository
                .findFirstInstrumentByStudioStudioName(studioName);

        return instrumentsMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public InstrumentDto findInstrByBandName(String bandName) {

        Optional<Instrument> entity = instrumentRepository
                .findFirstInstrumentByRelease_BandName(bandName);

        return instrumentsMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public InstrumentDto findInstrByReleaseName(String releaseName) {

        Optional<Instrument> entity = instrumentRepository
                .findFirstInstrumentByReleaseReleaseName(releaseName);

        return instrumentsMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstrumentDto> findAllInstruments(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Instrument> entityList = instrumentRepository.findAll(pageable);

        return entityList.getContent()
                .stream()
                .map(instrumentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstrumentDto> findAllInstrByStudio(String studioName, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Instrument> entityList = instrumentRepository
                .findAllByStudioStudioName(studioName, pageable);

        return entityList.getContent().stream()
                .map(instrumentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstrumentDto> findAllInstrByBandName(String bandName, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Instrument> entityList = instrumentRepository
                .findAllByReleaseBandName(bandName, pageable);

        return entityList.getContent().stream()
                .map(instrumentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstrumentDto> findAllInstrByType(String type, int page, int size) {
        InstrumentTypeEnum parsedType = InstrumentTypeEnum.parse(type);

        Pageable pageable = PageRequest.of(page, size);

        Page<Instrument> entityList = instrumentRepository
                .findAllByInstrumentType(parsedType, pageable);

        return entityList.getContent().stream()
                .map(instrumentsMapper::toDto)
                .collect(Collectors.toList());
    }
}
