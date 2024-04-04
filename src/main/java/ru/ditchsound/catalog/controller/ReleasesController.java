package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<ReleaseDto>> getAllReleases(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(releaseService.findAll(page, size), HttpStatus.OK);
    }

    @GetMapping("byBandName/{bandName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByBandName(@PathVariable("bandName")
                                                                         String bandName,
                                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(releaseService.findReleaseByBandName(bandName, page, size), HttpStatus.OK);
    }

    @GetMapping("byStatus/{status}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByStatus(@PathVariable("status") String status,
                                                               @RequestParam(required = false, defaultValue = "0") int page,
                                                               @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(releaseService.findByStatus(status, page, size), HttpStatus.OK);
    }

    @GetMapping("byLabelName/{labelName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByLabelName(@PathVariable("labelName") String labelName,
                                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(releaseService.findByLabelName(labelName, page, size), HttpStatus.OK);
    }

    @GetMapping("byGenreName/{genreName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByGenreName(@PathVariable("genreName") String genre,
                                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(releaseService.findByGenre(genre, page, size), HttpStatus.OK);
    }

    @GetMapping("byPrice/{price}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByPrice(@PathVariable("price") Double price,
                                                               @RequestParam(required = false, defaultValue = "0") int page,
                                                               @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(releaseService.findByPrice(price, page, size), HttpStatus.OK);
    }

}
