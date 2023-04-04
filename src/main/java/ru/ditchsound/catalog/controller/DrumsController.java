package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.DrumsDto;
import ru.ditchsound.catalog.service.DrumsService;

import java.util.List;

@RestController
@RequestMapping("/drums")
//@Tag(name = "Барабаны", description = "методы для работы с барабанами")
@RequiredArgsConstructor
public class DrumsController {
    private final DrumsService drumsService;

    @GetMapping("/{id}")
    public ResponseEntity<DrumsDto> getDrums(@PathVariable("id") Long id){
        return new ResponseEntity<>(drumsService.findDrumsById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<DrumsDto>> getAllDrums (){
        return new ResponseEntity<>(drumsService.findAllDrums(), HttpStatus.OK);
    }

    @GetMapping("/band/{bandName}")
    public ResponseEntity<List<DrumsDto>> getDrumsByBandName (@PathVariable("bandName") String bandName){
        return new ResponseEntity<>(drumsService.findDrumsByBandName(bandName), HttpStatus.OK);
    }

    @GetMapping("byDrumsType/{type}")
    public ResponseEntity<List<DrumsDto>> getDrumsByType (@PathVariable("type") String type){
        return new ResponseEntity<>(drumsService.findDrumsByType(type), HttpStatus.OK);
    }

//    @GetMapping("/studio/{studio}")
//    public ResponseEntity<List<Drums>> getDrumsByStudio (@PathVariable("studio") String studioName){
//        return new ResponseEntity<>(drumsService.findDrumsByStudio(studioName), HttpStatus.OK);
//    }

}
