package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;

import java.util.List;

public interface VocalService {

    InstrumentDto getVocalByVocalMic(String vocalMic);

    InstrumentDto getVocalByVocalTechnique(String vocalTechnique);

    List<InstrumentDto> getAllVocalsByVocalMic(String vocalMic, int page, int size);

    List<InstrumentDto> getAllVocalsByVocalTechnique(String vocalTechnique, int page, int size);

}
