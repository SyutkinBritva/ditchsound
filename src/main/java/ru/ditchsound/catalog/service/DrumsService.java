package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;

import java.util.List;

public interface DrumsService {

    InstrumentDto getDrumsByDrumMics(String drumsMics);

    List<InstrumentDto> getAllDrumsByDrumMics(String drumsMics, int page, int size);

}
