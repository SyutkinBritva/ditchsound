package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.Guitar.GuitarDto;

import java.util.List;

/** Сервис инструмента Guitar **/
public interface GuitarService {

    /** поиск гитары по айди **/
    GuitarDto findById (Long id);
    /** поиск всех гитар **/
    List<GuitarDto> findAllGuitars (int page, int size);
    /** поиск гитар по типу **/
    List<GuitarDto> findByGuitarType (String type, int page, int size);
    /** поиск всех гитар по имени группы **/
    List<GuitarDto> findByBandName (String bandName, int page, int size);
    /** поиск всех гитар по названию студии **/
    List<GuitarDto> findByStudioName (String studioName, int page, int size);
}
