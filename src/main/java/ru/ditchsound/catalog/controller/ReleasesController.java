package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.ReleaseDto;
import ru.ditchsound.catalog.service.ReleaseService;

import java.util.List;

@RestController
@RequestMapping("/release")
//@Tag(name = "Релизы", description = "методы для работы с релизами")
@RequiredArgsConstructor
public class ReleasesController {

    private final ReleaseService releaseService;

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseDto> getRelease(@PathVariable("id") Long id) {
        return new ResponseEntity<>(releaseService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ReleaseDto>> getAllReleases(){
        return new ResponseEntity<>(releaseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("byBandName/{bandName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByBandName(@PathVariable("bandName") String bandName){
        return new ResponseEntity<>(releaseService.findReleaseByBandName(bandName), HttpStatus.OK);
    }

   @GetMapping("byStatus/{status}")
   public ResponseEntity<List<ReleaseDto>> getReleaseByStatus(@PathVariable("status") String status){
       return new ResponseEntity<>(releaseService.findByStatus(status), HttpStatus.OK);
    }


}
