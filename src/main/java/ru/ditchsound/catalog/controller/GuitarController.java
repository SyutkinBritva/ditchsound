//package ru.ditchsound.catalog.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.ditchsound.catalog.model.Guitar;
//import ru.ditchsound.catalog.service.GuitarService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/guitars")
//@RequiredArgsConstructor
//public class GuitarController {
//
//    private final GuitarService guitarService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Guitar> getGuitar (@PathVariable("id") Long id){
//        return new ResponseEntity<>(guitarService.findById(id), HttpStatus.OK);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Guitar>> getAllGuitars (){
//        return new ResponseEntity<>(guitarService.findAllGuitars(), HttpStatus.OK);
//    }
//
//    @GetMapping("/type/{type}")
//    public ResponseEntity<List<Guitar>> getByGuitarType (@PathVariable("type") String type){
//        return new ResponseEntity<>(guitarService.findByGuitarType(type), HttpStatus.OK);
//    }
//
//    @GetMapping("/band/{bandName}")
//    public ResponseEntity<List<Guitar>> getByBandName (@PathVariable("bandName") String name){
//        return new ResponseEntity<>(guitarService.findByBandName(name), HttpStatus.OK);
//    }
//
//
//}
