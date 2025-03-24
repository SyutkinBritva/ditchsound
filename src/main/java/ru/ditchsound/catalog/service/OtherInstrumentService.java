package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;

import java.util.List;

public interface OtherInstrumentService {

    InstrumentDto findOtherInstrumentByPreset(String preset);

    List<InstrumentDto> findAllInstrumentsByPreset(String preset, int page, int size);
}
