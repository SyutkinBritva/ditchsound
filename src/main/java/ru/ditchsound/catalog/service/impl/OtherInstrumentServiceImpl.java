package ru.ditchsound.catalog.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.mappers.instruments.InstrumentsMapper;
import ru.ditchsound.catalog.model.OtherInstrument;
import ru.ditchsound.catalog.repository.OtherInstrumentRepository;
import ru.ditchsound.catalog.service.OtherInstrumentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OtherInstrumentServiceImpl implements OtherInstrumentService {

    private final OtherInstrumentRepository otherInstrumentRepository;

    private final InstrumentsMapper instrumentsMapper;

    @Transactional(readOnly = true)
    @Override
    public InstrumentDto findOtherInstrumentByPreset(String preset) {
        Optional<OtherInstrument> entity = otherInstrumentRepository.findFirstByPreset(preset);
        return instrumentsMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstrumentDto> findAllInstrumentsByPreset(String preset, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        List<OtherInstrument> instruments = otherInstrumentRepository.findAllByPreset(preset, pageable);

        return instruments.stream().map(instrumentsMapper::toDto).collect(Collectors.toList());
    }
}
