package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.enums.InstrumentTypeEnum;
import ru.ditchsound.catalog.service.InstrumentService;

import java.util.List;

@RestController
@RequestMapping("/instruments")
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentService instrumentService;

    @GetMapping("/{id}")
    public InstrumentDto getInstrumentById(@PathVariable("id") Long id) {

        return instrumentService.findInstrById(id);
    }

    @GetMapping("by-studio/{studioName}")
    public InstrumentDto getInstrumentByStudio(@PathVariable("studioName") String studioName) {

        return instrumentService.findInstrByStudio(studioName);
    }

    @GetMapping("by-band/{bandName}")
    public InstrumentDto getInstrumentByBandName(@PathVariable("bandName") String bandName) {

        return instrumentService.findInstrByBandName(bandName);
    }

    @GetMapping("by-release/{releaseName}")
    public InstrumentDto getInstrumentByReleaseName(@PathVariable("releaseName") String releaseName) {

        return instrumentService.findInstrByReleaseName(releaseName);
    }

    @GetMapping()
    public ResponseEntity<List<InstrumentDto>> getAllInstr(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(instrumentService.findAllInstruments(page, size), HttpStatus.OK);
    }

    @GetMapping("by-band/all/{bandName}")
    public ResponseEntity<List<InstrumentDto>> getAllInstrByBandName(@PathVariable("bandName") String bandName
            , @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(instrumentService.findAllInstrByBandName(bandName, page, size), HttpStatus.OK);
    }

    @GetMapping("byInstrType/all/{type}")
    public ResponseEntity<List<InstrumentDto>> getAllInstrByType(@PathVariable("type") String type
            , @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(instrumentService.findAllInstrByType(InstrumentTypeEnum.valueOf(type), page, size), HttpStatus.OK);
    }

    @GetMapping("by-studio/all/{studio}")
    public ResponseEntity<List<InstrumentDto>> getAllInstrByStudio(
            @PathVariable("studio") String studio
            , @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "5") int size
    ) {

        return new ResponseEntity<>(instrumentService.findAllInstrByStudio(studio, page, size), HttpStatus.OK);
    }

}
