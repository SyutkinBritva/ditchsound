package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.model.Drums;

import java.util.List;
/** Сервис инструмента Drums **/

public interface DrumsService {

    /** поиск Drum инструмента по id **/
    Drums findDrumsById (Long id);

    /** поиск всех drums инструментов по id **/
    List<Drums> findAllDrums ();

    /** поиск всех drums инструментов по имени студии **/
    List<Drums> findDrumsByStudio (String name);

    /** поиск всех drums инструментов по имени исполнителя **/
    List<Drums> findDrumsByBandName (String name);

    /** поиск всех drums инструментов по типу**/
    List<Drums> findDrumsByType (String type);
}
