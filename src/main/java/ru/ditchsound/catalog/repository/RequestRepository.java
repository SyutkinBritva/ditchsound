package ru.ditchsound.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
