package ru.ditchsound.catalog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

}
