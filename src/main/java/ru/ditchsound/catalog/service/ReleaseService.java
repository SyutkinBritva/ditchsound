package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.dto.ReleaseDto;

import java.util.List;

public interface ReleaseService {
    /** поиск релиза по id **/
ReleaseDto findById(Long id);
    /** поиск всех релизов**/
List<ReleaseDto> findAll();
    /** поиск релиза по имени исполнителя**/
List<ReleaseDto> findReleaseByBandName(String name);
    /** поиск релиза по статусу**/
List<ReleaseDto> findByStatus(String status);

}
