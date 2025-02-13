package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;

import java.util.List;

public interface InstrumentService {
    /** поиск инструмента по ID**/
    InstrumentDto findInstrById(Long id);

    /** поиск всех Instr инструментов**/
    List<InstrumentDto> findAllInstruments(int page, int size);

    /** поиск всех Instr инструментов по имени студии **/
    List<InstrumentDto> findInstrByStudio (String name, int page, int size);

    /** поиск всех Instr инструментов по имени исполнителя **/
    List<InstrumentDto> findInstrByBandName(String name, int page, int size);

    /** поиск всех Instr инструментов по типу**/
    List<InstrumentDto> findInstrByType(String type, int page, int size);
}
