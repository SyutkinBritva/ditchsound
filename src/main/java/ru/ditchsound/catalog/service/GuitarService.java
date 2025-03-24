package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;

import java.util.List;

public interface GuitarService {

    InstrumentDto getGuitarBySignalChain(String signalChain);

    List<InstrumentDto> getAllGuitarsBySignalChain(String signalChain, int page, int size);

}
