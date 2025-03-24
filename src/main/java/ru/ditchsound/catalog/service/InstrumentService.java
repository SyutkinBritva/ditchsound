package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.enums.InstrumentTypeEnum;

import java.util.List;

public interface InstrumentService {
    /** поиск инструмента по ID**/
    InstrumentDto findInstrById(Long id);

    /** поиск инструмента по имени студии**/
    InstrumentDto findInstrByStudio(String studioName);

    /** поиск инструмента по имени исполнителя**/
    InstrumentDto findInstrByBandName(String bandName);

    /** поиск инструмента по названию релиза**/
    InstrumentDto findInstrByReleaseName(String releaseName);

    /** поиск всех Instr инструментов**/
    List<InstrumentDto> findAllInstruments(int page, int size);

    /** поиск всех Instr инструментов по имени студии **/
    List<InstrumentDto> findAllInstrByStudio(String studioName, int page, int size);

    /** поиск всех Instr инструментов по имени исполнителя **/
    List<InstrumentDto> findAllInstrByBandName(String bandName, int page, int size);

    /** поиск всех Instr инструментов по типу **/
    List<InstrumentDto> findAllInstrByType(InstrumentTypeEnum type, int page, int size);
}
