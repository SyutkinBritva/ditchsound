package ru.ditchsound.catalog.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@SecurityRequirement(name = "bearerAuth")
public class ReleasesController {

    private final ReleaseService releaseService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ReleaseDto> getRelease(@PathVariable Long id) {
        log.info("GET /api/releases/{} - Получение релиза", id);
        return ResponseEntity.ok(releaseService.findById(id));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<ReleaseDto>> getAllReleases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/releases?page={}&size={} - Получение всех релизов", page, size);
        return ResponseEntity.ok(releaseService.findAll(page, size));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-band/{bandName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByBandName(
            @PathVariable String bandName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/releases/by-band/{} - Получение релизов по группе", bandName);
        return ResponseEntity.ok(releaseService.findReleaseByBandName(bandName, page, size));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-label/{labelName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByLabelName(
            @PathVariable String labelName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/releases/by-label/{} - Получение релизов по лейблу", labelName);
        return ResponseEntity.ok(releaseService.findByLabelName(labelName, page, size));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-genre/{genreName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByGenreName(
            @PathVariable GenreEnum genreName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/releases/by-genre/{} - Получение релизов по жанру", genreName);
        return ResponseEntity.ok(releaseService.findByGenre(genreName, page, size));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping
    public ResponseEntity<ReleaseResultDto> updateRelease(@RequestBody ReleaseUpdateDto release) {
        log.info("PUT /api/releases - Обновление релиза");
        ReleaseResultDto updated = releaseService.updateRelease(release);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
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
