package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.enums.InstrumentTypeEnum;
import ru.ditchsound.catalog.exception.BadRequestException;
import ru.ditchsound.catalog.service.InstrumentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/instruments")
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentService instrumentService;

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentDto> getInstrumentById(@PathVariable Long id) {
        log.info("GET /api/instruments/{} - Получение инструмента по ID", id);
        return ResponseEntity.ok(instrumentService.findInstrById(id));
    }

    @GetMapping("/by-studio/{studioName}")
    public ResponseEntity<InstrumentDto> getInstrumentByStudio(@PathVariable String studioName) {
        log.info("GET /api/instruments/by-studio/{} - Получение инструмента по студии", studioName);
        return ResponseEntity.ok(instrumentService.findInstrByStudio(studioName));
    }

    @GetMapping("/by-band/{bandName}")
    public ResponseEntity<InstrumentDto> getInstrumentByBandName(@PathVariable String bandName) {
        log.info("GET /api/instruments/by-band/{} - Получение инструмента по группе", bandName);
        return ResponseEntity.ok(instrumentService.findInstrByBandName(bandName));
    }

    @GetMapping("/by-release/{releaseName}")
    public ResponseEntity<InstrumentDto> getInstrumentByReleaseName(@PathVariable String releaseName) {
        log.info("GET /api/instruments/by-release/{} - Получение инструмента по релизу", releaseName);
        return ResponseEntity.ok(instrumentService.findInstrByReleaseName(releaseName));
    }

    @GetMapping
    public ResponseEntity<List<InstrumentDto>> getAllInstr(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/instruments?page={}&size={} - Получение всех инструментов", page, size);
        return ResponseEntity.ok(instrumentService.findAllInstruments(page, size));
    }

    @GetMapping("/by-band/all/{bandName}")
    public ResponseEntity<List<InstrumentDto>> getAllInstrByBandName(
            @PathVariable String bandName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/instruments/by-band/all/{} - Получение всех инструментов по группе", bandName);
        return ResponseEntity.ok(instrumentService.findAllInstrByBandName(bandName, page, size));
    }

    @GetMapping("/by-type/all/{type}")
    public ResponseEntity<List<InstrumentDto>> getAllInstrByType(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("GET /api/instruments/by-type/all/{} - Получение всех инструментов по типу", type);
        try {
            InstrumentTypeEnum instrumentType = InstrumentTypeEnum.valueOf(type.toUpperCase());
            return ResponseEntity.ok(instrumentService.findAllInstrByType(instrumentType, page, size));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Некорректный тип инструмента: " + type);
        }
    }

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
