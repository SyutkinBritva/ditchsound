package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Release.ReleaseResultDto;
import ru.ditchsound.catalog.dto.Release.ReleaseUpdateDto;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.service.ReleaseService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/releases")
@RequiredArgsConstructor
public class ReleasesController {

    private final ReleaseService releaseService;

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseDto> getRelease(@PathVariable Long id) {
        log.info("GET /api/releases/{} - Получение релиза", id);
        return ResponseEntity.ok(releaseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReleaseDto>> getAllReleases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/releases?page={}&size={} - Получение всех релизов", page, size);
        return ResponseEntity.ok(releaseService.findAll(page, size));
    }

    @GetMapping("/by-band/{bandName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByBandName(
            @PathVariable String bandName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/releases/by-band/{} - Получение релизов по группе", bandName);
        return ResponseEntity.ok(releaseService.findReleaseByBandName(bandName, page, size));
    }

    @GetMapping("/by-label/{labelName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByLabelName(
            @PathVariable String labelName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/releases/by-label/{} - Получение релизов по лейблу", labelName);
        return ResponseEntity.ok(releaseService.findByLabelName(labelName, page, size));
    }

    @GetMapping("/by-genre/{genreName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByGenreName(
            @PathVariable GenreEnum genreName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/releases/by-genre/{} - Получение релизов по жанру", genreName);
        return ResponseEntity.ok(releaseService.findByGenre(genreName, page, size));
    }

    @PutMapping
    public ResponseEntity<ReleaseResultDto> updateRelease(@RequestBody ReleaseUpdateDto release) {
        log.info("PUT /api/releases - Обновление релиза");
        ReleaseResultDto updated = releaseService.updateRelease(release);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{bandName}/{releaseName}/instruments")
    public ResponseEntity<ReleaseResultDto> addInstrumentToRelease(
            @PathVariable String bandName,
            @PathVariable String releaseName,
            @RequestBody InstrumentDto instrumentDto
    ) {
        log.info("PUT /api/releases/{}/{}/instruments - Добавление инструмента в релиз", bandName, releaseName);
        ReleaseResultDto result = releaseService.addInstrumentToRelease(instrumentDto, bandName, releaseName);
        return ResponseEntity.status(201).body(result);
    }
}
