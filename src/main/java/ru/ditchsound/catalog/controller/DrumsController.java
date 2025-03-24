package ru.ditchsound.catalog.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.service.DrumsService;

import java.util.List;

@RestController
@RequestMapping("/drums")
@AllArgsConstructor
public class DrumsController {

    private final DrumsService drumsService;

    @GetMapping("by-drumMics/{drumMics}")
    public InstrumentDto getDrumsByDrumsMics(@PathVariable String drumMics){

        return drumsService.getDrumsByDrumMics(drumMics);

    }

    @GetMapping("all-by-drumMics/{drumMics}")
    public List<InstrumentDto> getAllDrumsByDrumsMics(@PathVariable String drumMics,
                                                      @RequestParam(required = false, defaultValue = "0") int page,
                                                      @RequestParam(required = false, defaultValue = "5") int size){

        return drumsService.getAllDrumsByDrumMics(drumMics, page, size);

    }

}
