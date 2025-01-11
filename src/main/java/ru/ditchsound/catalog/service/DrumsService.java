package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.DrumsDto;
import ru.ditchsound.catalog.model.Drums;

import java.util.List;
/** Сервис инструмента Drums **/

public interface DrumsService {

    /** поиск Drum инструмента по id **/
    DrumsDto findDrumsById(Long id);

    /** поиск всех drums инструментов**/
    List<DrumsDto> findAllDrums(int page, int size);

    /** поиск всех drums инструментов по имени студии **/
    List<DrumsDto> findDrumsByStudio (String name, int page, int size);

    /** поиск всех drums инструментов по имени исполнителя **/
    List<DrumsDto> findDrumsByBandName(String name, int page, int size);

    /** поиск всех drums инструментов по типу**/
    List<DrumsDto> findDrumsByType(String type, int page, int size);

    Long createDrums (Drums drums);
}
