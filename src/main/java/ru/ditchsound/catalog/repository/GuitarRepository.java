//package ru.ditchsound.catalog.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import ru.ditchsound.catalog.model.Guitar;
//
//import java.util.List;
//
//public interface GuitarRepository extends JpaRepository<Guitar, Long> {
//
//    List<Guitar> findAllByReleaseBandNameIgnoreCase(String name);
//    List<Guitar> findAllByGuitarType(String type);
//}
