package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.model.Drums;
import ru.ditchsound.catalog.service.DrumsService;

import java.util.List;

@RestController
@RequestMapping("/drums")
//@Tag(name = "Барабаны", description = "методы для работы с барабанами")
@RequiredArgsConstructor
public class DrumsController {
    private final DrumsService drumsService;

    @GetMapping("/{id}")
    public ResponseEntity<Drums> getDrums (@PathVariable("id") Long id){
        return new ResponseEntity<>(drumsService.findDrumsById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Drums>> getAllDrums (){
        return new ResponseEntity<>(drumsService.findAllDrums(), HttpStatus.OK);
    }

    @GetMapping("/band/{bandName}")
    public ResponseEntity<List<Drums>> getDrumsByBandName (@PathVariable("bandName") String bandName){
        return new ResponseEntity<>(drumsService.findDrumsByBandName(bandName), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Drums>> getDrumsByType (@PathVariable("type") String type){
        return new ResponseEntity<>(drumsService.findDrumsByType(type), HttpStatus.OK);
    }

    @GetMapping("/studio/{studio}")
    public ResponseEntity<List<Drums>> getDrumsByStudio (@PathVariable("studio") String studioName){
        return new ResponseEntity<>(drumsService.findDrumsByStudio(studioName), HttpStatus.OK);
    }

}
