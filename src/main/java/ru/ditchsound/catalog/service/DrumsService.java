package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.DrumsDto;

import java.util.List;
/** Сервис инструмента Drums **/

public interface DrumsService {

    /** поиск Drum инструмента по id **/
    DrumsDto findDrumsById(Long id);

    /** поиск всех drums инструментов по id **/
    List<DrumsDto> findAllDrums(int page, int size);

//    /** поиск всех drums инструментов по имени студии **/
//    List<Drums> findDrumsByStudio (String name);
//
    /** поиск всех drums инструментов по имени исполнителя **/
    List<DrumsDto> findDrumsByBandName(String name);
//
    /** поиск всех drums инструментов по типу**/
    List<DrumsDto> findDrumsByType(String type);
}
