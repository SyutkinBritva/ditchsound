package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.service.OtherInstrumentService;

import java.util.List;

@RestController
@RequestMapping("/otherInstrument")
@RequiredArgsConstructor
public class OtherInstrumentController {

    private final OtherInstrumentService otherInstrumentService;

    @GetMapping("/by-preset{preset}")
    public InstrumentDto getOtherInstrumentByPreset(@PathVariable String preset) {

        return otherInstrumentService.findOtherInstrumentByPreset(preset);
    }

    @GetMapping("/all-by-preset{preset}")
    public List<InstrumentDto> getAllOtherInstrumentByPreset(@PathVariable String preset,
                                                             @RequestParam(required = false, defaultValue = "0") int page,
                                                             @RequestParam(required = false, defaultValue = "5") int size) {

        return otherInstrumentService.findAllInstrumentsByPreset(preset, page, size);
    }

}
