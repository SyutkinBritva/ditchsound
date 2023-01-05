package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.model.Vocal;
import ru.ditchsound.catalog.repository.VocalRepository;

import java.util.List;

public class VocalServiceImpl {
    private final VocalRepository vocalRepository;

    public VocalServiceImpl(VocalRepository vocalRepository) {
        this.vocalRepository = vocalRepository;
    }

    List<Vocal> findAllVocals (){
        return vocalRepository.findAll();
    }
    public Vocal findByIdVocal (Long id) {
       return vocalRepository.findById(id).orElse(null);
    }

    List<Vocal> findByVocalType (String type){
        return vocalRepository.findAllByVocalType(type);
    }

    List<Vocal> findByBandName (String bandName){
        return vocalRepository.findAllByReleaseBandName(bandName);
    }

}
