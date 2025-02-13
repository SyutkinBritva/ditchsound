package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.Vocal.VocalDto;

import java.util.List;

/** Сервис инструмента Vocals **/
    public interface VocalService {

    /** поиск всех vocals инструментов по id **/
    VocalDto findById (long id);

    /** поиск всех vocals инструментов **/
    List<VocalDto> findAll(int page, int size);

    /** поиск всех vocals инструментов по имени студии **/
    List<VocalDto> findAllByStudioName (String studioName, int page, int size);

    /** поиск всех vocals инструментов по имени артиста **/
    List<VocalDto> findAllByArtistName (String artistName, int page, int size);

}
