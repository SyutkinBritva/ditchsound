package ru.ditchsound.catalog.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.service.InstrumentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/instruments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class InstrumentController {

    private final InstrumentService instrumentService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<InstrumentDto> getInstrumentById(@PathVariable Long id) {
        log.info("GET /api/instruments/{} - Получение инструмента по ID", id);
        return ResponseEntity.ok(instrumentService.findInstrById(id));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-studio/{studioName}")
    public ResponseEntity<InstrumentDto> getInstrumentByStudio(@PathVariable String studioName) {
        log.info("GET /api/instruments/by-studio/{} - Получение инструмента по студии", studioName);
        return ResponseEntity.ok(instrumentService.findInstrByStudio(studioName));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-band/{bandName}")
    public ResponseEntity<InstrumentDto> getInstrumentByBandName(@PathVariable String bandName) {
        log.info("GET /api/instruments/by-band/{} - Получение инструмента по группе", bandName);
        return ResponseEntity.ok(instrumentService.findInstrByBandName(bandName));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-release/{releaseName}")
    public ResponseEntity<InstrumentDto> getInstrumentByReleaseName(@PathVariable String releaseName) {
        log.info("GET /api/instruments/by-release/{} - Получение инструмента по релизу", releaseName);
        return ResponseEntity.ok(instrumentService.findInstrByReleaseName(releaseName));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<InstrumentDto>> getAllInstr(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/instruments?page={}&size={} - Получение всех инструментов", page, size);
        return ResponseEntity.ok(instrumentService.findAllInstruments(page, size));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-band/all/{bandName}")
    public ResponseEntity<List<InstrumentDto>> getAllInstrByBandName(
            @PathVariable String bandName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/instruments/by-band/all/{} - Получение всех инструментов по группе", bandName);
        return ResponseEntity.ok(instrumentService.findAllInstrByBandName(bandName, page, size));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-type/all/{type}")
    public ResponseEntity<List<InstrumentDto>> getAllInstrByType(
            @PathVariable("type") String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /instruments/by-type/all/{} - Получение всех инструментов по типу", type);
        return ResponseEntity.ok(instrumentService.findAllInstrByType(type, page, size));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/by-studio/all/{studio}")
    public ResponseEntity<List<InstrumentDto>> getAllInstrByStudio(
            @PathVariable String studio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/instruments/by-studio/all/{} - Получение всех инструментов по студии", studio);
        return ResponseEntity.ok(instrumentService.findAllInstrByStudio(studio, page, size));
    }
}
