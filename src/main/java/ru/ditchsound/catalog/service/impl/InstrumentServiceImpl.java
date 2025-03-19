package ru.ditchsound.catalog.service.impl;

import org.springframework.stereotype.Service;
import ru.ditchsound.catalog.repository.InstrumentRepository;
import ru.ditchsound.catalog.service.InstrumentService;

@Service
public class InstrumentServiceImpl implements InstrumentService {
    private final InstrumentRepository instrumentRepository;

    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }


//    @Transactional(readOnly = true)
//    public InstrumentDto findInstrById(Long id) {
//        Instrument instrument = instrumentRepository.findById(id).
//                orElseThrow(
//                () -> new RuntimeException(String.format("в базе нет барабанов с переданным id %s", id)));
//        return instrumentMapper.toDto(instrument);
//    }
//
//    @Transactional(readOnly = true)
//    public List<InstrumentDto> findAllInstruments(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Instrument> instruments = instrumentRepository.findAll(pageable);
//        return instruments.stream().
//                map(instrumentMapper::toDto).
//                collect(Collectors.toList());
//
//    }
//
//    @Transactional(readOnly = true)
//    public List<InstrumentDto> findInstrByStudio(String name, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Instrument> instruments = instrumentRepository.
//                findAllByStudioStudioNameIgnoreCase(name, pageable);
//        return instruments.stream().
//                map(instrumentMapper::toDto).
//                collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    public List<InstrumentDto> findInstrByBandName(String name, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Instrument> instruments = instrumentRepository.
//                findAllByReleaseBandNameIgnoreCase(name, pageable);
//        return instruments.stream().
//                map(instrumentMapper::toDto).
//                collect(Collectors.toList());
//    }

}
