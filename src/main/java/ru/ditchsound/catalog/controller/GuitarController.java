package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ditchsound.catalog.dto.GuitarDto;
import ru.ditchsound.catalog.service.GuitarService;

import java.util.List;
@RestController
@RequestMapping("/guitars")
@RequiredArgsConstructor
public class GuitarController {

    private final GuitarService guitarService;

    @GetMapping("/{id}")
    public ResponseEntity<GuitarDto> getGuitarById(@PathVariable("id") Long id){
        return new ResponseEntity<>(guitarService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<GuitarDto>> getAllGuitars (
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(guitarService.findAllGuitars(page, size), HttpStatus.OK);
    }

    @GetMapping("/studio/{studio}")
    public ResponseEntity<List<GuitarDto>> getAllGuitarsByStudio (@PathVariable("studio") String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(guitarService.findByStudioName(name, page, size), HttpStatus.OK);
    }
    @GetMapping("byGuitarType/{type}")
    public ResponseEntity<List<GuitarDto>> getAllGuitarsByType (@PathVariable("type") String type,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(guitarService.findByGuitarType(type, page, size), HttpStatus.OK);
    }
    @GetMapping("/band/{bandName}")
    public ResponseEntity<List<GuitarDto>> getAllGuitarsByBandName (@PathVariable("bandName")String bandName,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ){
        return new ResponseEntity<>(guitarService.findByBandName(bandName, page, size), HttpStatus.OK);
    }
}
