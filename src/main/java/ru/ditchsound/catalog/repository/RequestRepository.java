package ru.ditchsound.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ditchsound.catalog.enums.RequestStatus;
import ru.ditchsound.catalog.model.Request;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findByBandName(String bandName);

    Optional<Request> findByRequestName(String requestName);

    Optional<Request> findByIdAndRequestStatus(Long id, RequestStatus status);
}
