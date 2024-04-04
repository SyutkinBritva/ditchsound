package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.ReleaseDto;

import java.util.List;

public interface ReleaseService {
    /**
     * поиск релиза по id
     **/
    ReleaseDto findById(Long id);

    /**
     * поиск всех релизов
     **/
    List<ReleaseDto> findAll(int page, int size);

    /**
     * поиск релиза по имени исполнителя
     **/
    List<ReleaseDto> findReleaseByBandName(String name, int page, int size);

    /**
     * поиск релиза по статусу
     **/
    List<ReleaseDto> findByStatus(String status, int page, int size);

    /**
     * поиск релиза по названию лейбла
     **/
    List<ReleaseDto> findByLabelName(String name, int page, int size);

    /** поиск релиза по жанру **/

    List<ReleaseDto> findByGenre (String genre, int page, int size);

    /** поиск релиза по цене **/

    List<ReleaseDto> findByPrice(Double price, int page, int size);


}
