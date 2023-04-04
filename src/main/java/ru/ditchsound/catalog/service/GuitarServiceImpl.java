//package ru.ditchsound.catalog.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.ditchsound.catalog.model.Guitar;
//import ru.ditchsound.catalog.repository.GuitarRepository;
//
//import java.util.List;
//
//@Service
//public class GuitarServiceImpl implements GuitarService {
//
//    private final GuitarRepository guitarRepositry;
//
//    public GuitarServiceImpl(GuitarRepository guitarRepositry) {
//        this.guitarRepositry = guitarRepositry;
//    }
//
//    @Transactional(readOnly = true)
//    public Guitar findById(Long id) {
//        Guitar guitar = guitarRepositry.findById(id).orElseThrow(() ->
//                new RuntimeException(String.format("в базе нет гитар с переданным id %s", id)));
//        return guitar;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Guitar> findAllGuitars() {
//
//        return guitarRepositry.findAll();
//    }
//
//    @Transactional(readOnly = true)
//    public List<Guitar> findByGuitarType(String type){
//        return guitarRepositry.findAllByGuitarType(type);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Guitar> findByBandName(String bandName){
//        return guitarRepositry.findAllByReleaseBandNameIgnoreCase(bandName);
//    }
//}
