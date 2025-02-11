package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
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

    @GetMapping()
    public ResponseEntity<List<InstrumentDto>> getAllInstr (
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(instrumentService.findAllInstruments(page, size), HttpStatus.OK);
    }
    @GetMapping("/band/{bandName}")
    public ResponseEntity<List<InstrumentDto>> getInstrByBandName (@PathVariable("bandName") String bandName
            , @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(instrumentService.findInstrByBandName(bandName, page, size), HttpStatus.OK);
    }

    @GetMapping("byInstrType/{type}")
    public ResponseEntity<List<InstrumentDto>> getInstrByType (@PathVariable("type") String type
            , @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(instrumentService.findInstrByType(type, page, size), HttpStatus.OK);
    }

    @GetMapping("/studio/{studio}")
    public ResponseEntity<List<InstrumentDto>> getInstrByStudio (
            @PathVariable("studio") String studio
            , @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "5") int size
    ){

        return new ResponseEntity<>(instrumentService.findInstrByStudio(studio, page, size), HttpStatus.OK);
    }
}
