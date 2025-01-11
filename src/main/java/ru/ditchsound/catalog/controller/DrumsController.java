package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ditchsound.catalog.dto.DrumsDto;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.service.DrumsService;

import java.util.List;
@RestController
@RequestMapping("/drums")
@RequiredArgsConstructor
public class DrumsController {
    private final DrumsService drumsService;

    @GetMapping("/{id}")
    public ResponseEntity<DrumsDto> getDrums(@PathVariable("id") Long id){
        return new ResponseEntity<>(drumsService.findDrumsById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<DrumsDto>> getAllDrums (
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(drumsService.findAllDrums(page, size), HttpStatus.OK);
    }

    @GetMapping("/band/{bandName}")
    public ResponseEntity<List<DrumsDto>> getDrumsByBandName (@PathVariable("bandName") String bandName
                                                               , @RequestParam(required = false, defaultValue = "0") int page
                                                               , @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(drumsService.findDrumsByBandName(bandName, page, size), HttpStatus.OK);
    }

    @GetMapping("byDrumsType/{type}")
    public ResponseEntity<List<DrumsDto>> getDrumsByType (@PathVariable("type") String type
                                                           , @RequestParam(required = false, defaultValue = "0") int page
                                                           , @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(drumsService.findDrumsByType(type, page, size), HttpStatus.OK);
    }

    @GetMapping("/studio/{studio}")
    public ResponseEntity<List<DrumsDto>> getDrumsByStudio (
            @PathVariable("studio") String studio
           , @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "5") int size
    ){

        return new ResponseEntity<>(drumsService.findDrumsByStudio(studio, page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createDrums(@RequestBody Drums drums){
        long id = drumsService.createDrums(drums);
        return new ResponseEntity<>(String.valueOf(id), HttpStatus.OK);
    }

}
