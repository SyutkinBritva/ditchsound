package ru.ditchsound.catalog.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.service.GuitarService;

import java.util.List;

@RestController
@RequestMapping("/guitar")
@AllArgsConstructor
public class GuitarController {

    private final GuitarService guitarService;

    @GetMapping("by-signalChain/{signalChain}")
    public InstrumentDto getGuitarBySignalChain(@PathVariable String signalChain) {

        return guitarService.getGuitarBySignalChain(signalChain);

    }

    @GetMapping("all-by-signalChain/{signalChain}")
    public List<InstrumentDto> getAllGuitarsBySignalChain(@PathVariable String signalChain,
                                                          @RequestParam(required = false, defaultValue = "0") int page,
                                                          @RequestParam(required = false, defaultValue = "5") int size){

        return guitarService.getAllGuitarsBySignalChain(signalChain, page, size);

    }
}
