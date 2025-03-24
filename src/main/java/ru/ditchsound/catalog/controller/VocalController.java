package ru.ditchsound.catalog.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Instrument.InstrumentDto;
import ru.ditchsound.catalog.service.VocalService;

import java.util.List;

@RestController
@RequestMapping("/vocal")
@AllArgsConstructor
public class VocalController {

    private final VocalService vocalService;

    @GetMapping("by-vocalMic/{vocalMic}")
    public InstrumentDto getVocalByVocalMic(@PathVariable String vocalMic){

        return vocalService.getVocalByVocalMic(vocalMic);
    }

    @GetMapping("by-vocalTechnique/{vocalTechnique}")
    public InstrumentDto getVocalByVocalTechnique(@PathVariable String vocalTechnique){

        return vocalService.getVocalByVocalTechnique(vocalTechnique);
    }

    @GetMapping("all-by-vocalMic/{vocalMic}")
    public List<InstrumentDto> getAllVocalsByVocalMic(@PathVariable String vocalMic,
                                                      @RequestParam(required = false, defaultValue = "0") int page,
                                                      @RequestParam(required = false, defaultValue = "5") int size){
        return vocalService.getAllVocalsByVocalMic(vocalMic, page, size);
    }

    @GetMapping("all-by-vocalTechnique/{vocalTechnique}")
    public List<InstrumentDto> getAllVocalsByVocalTechnique(@PathVariable String vocalTechnique,
                                                      @RequestParam(required = false, defaultValue = "0") int page,
                                                      @RequestParam(required = false, defaultValue = "5") int size){
        return vocalService.getAllVocalsByVocalMic(vocalTechnique, page, size);
    }
}
