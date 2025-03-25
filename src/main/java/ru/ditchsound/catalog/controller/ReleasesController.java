package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.dto.Release.ReleaseDto;
import ru.ditchsound.catalog.dto.Release.ReleaseResultDto;
import ru.ditchsound.catalog.dto.Release.ReleaseUpdateDto;
import ru.ditchsound.catalog.enums.GenreEnum;
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

    @GetMapping("byLabelName/{labelName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByLabelName(@PathVariable("labelName") String labelName,
                                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(releaseService.findByLabelName(labelName, page, size), HttpStatus.OK);
    }

    @GetMapping("byGenreName/{genreName}")
    public ResponseEntity<List<ReleaseDto>> getReleaseByGenreName(@PathVariable("genreName") GenreEnum genre,
                                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(releaseService.findByGenre(genre, page, size), HttpStatus.OK);
    }

    @PutMapping("update/")
    public ResponseEntity<ReleaseResultDto> updateRelease(@RequestBody ReleaseUpdateDto release) {
        ReleaseResultDto releaseDto = releaseService.updateRelease(release);
        return ResponseEntity.ok(releaseDto);
    }

    @PutMapping("/{bandName}/{releaseName}/instruments")
    public ResponseEntity<ReleaseResultDto> addInstrumentToRelease(@PathVariable String bandName,
                                                                   @PathVariable String releaseName,
                                                                   @RequestBody InstrumentDto instrumentDto){
        ReleaseResultDto resultDto = releaseService.addInstrumentToRelease(instrumentDto, bandName, releaseName);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDto);
    }
}
