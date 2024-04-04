package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ditchsound.catalog.dto.VocalDto;
import ru.ditchsound.catalog.service.VocalService;

import java.util.List;

@RestController
@RequestMapping("/vocals")
@RequiredArgsConstructor
public class VocalController {

    private final VocalService vocalService;

    @GetMapping("/{id}")
    public ResponseEntity<VocalDto> getVocalById(@PathVariable("id") Long id){
        return new ResponseEntity<>(vocalService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<VocalDto>> getAllVocals(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size){
        return new ResponseEntity<>(vocalService.findAll(page, size), HttpStatus.OK);

    }

    @GetMapping("/studio/{studioName}")
    public ResponseEntity<List<VocalDto>> getAllVocalsByStudioName(@PathVariable("studioName") String studioName,
                                                                   @RequestParam(required = false, defaultValue = "0") int page,
                                                                   @RequestParam(required = false, defaultValue = "5") int size){
        return new ResponseEntity<>(vocalService.findAllByStudioName(studioName, page, size), HttpStatus.OK);
    }

    @GetMapping("/band/{bandName}")
    public ResponseEntity<List<VocalDto>> getAllVocalsByArtist(@PathVariable("bandName") String bandName,
                                                               @RequestParam(required = false, defaultValue = "0") int page,
                                                               @RequestParam(required = false, defaultValue = "5") int size){
        return new ResponseEntity<>(vocalService.findAllByArtistName(bandName, page, size), HttpStatus.OK);
    }



}
