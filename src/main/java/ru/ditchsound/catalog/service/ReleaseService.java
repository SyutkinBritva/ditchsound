package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Release.ReleaseResultDto;
import ru.ditchsound.catalog.dto.Release.ReleaseUpdateDto;
import ru.ditchsound.catalog.enums.GenreEnum;

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
     * поиск релиза по названию лейбла
     **/
    List<ReleaseDto> findByLabelName(String name, int page, int size);

    /** поиск релиза по жанру **/

    List<ReleaseDto> findByGenre (GenreEnum genre, int page, int size);

    /** поиск релиза по цене **/

   // List<ReleaseDto> findByPrice(Double price, int page, int size);

    /** обновление релиза **/
    ReleaseResultDto updateRelease (Long id, ReleaseUpdateDto releaseUpdateDto);
}
