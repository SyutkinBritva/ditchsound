package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Request.RequestApprovedDto;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.dto.Request.RequestStatusUpdateDto;
import ru.ditchsound.catalog.service.impl.RequestServiceImpl;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestServiceImpl requestService;

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getRequest(@PathVariable("id") Long requestId) {
        log.info("GET /api/requests/{} - Получение заявки", requestId);
        RequestDto request = requestService.findRequest(requestId);
        return ResponseEntity.ok(request);
    }

    @GetMapping()
    public ResponseEntity<List<RequestDto>> getAllRequests(@RequestParam(required = false, defaultValue = "0") int page,
                                                           @RequestParam(required = false, defaultValue = "5") int size) {
        log.info("GET /api/requests?page={}&size={} - Получение всех заявок", page, size);
        List<RequestDto> requests = requestService.findAllRequests(page, size);
        return ResponseEntity.ok(requests);
    }

    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto) {
        log.info("POST /api/requests - Создание новой заявки");
        RequestDto created = requestService.createRequest(requestDto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}/approve-request")
    public ResponseEntity<RequestApprovedDto> approveRequest(@PathVariable Long id, @RequestParam Double discount) {
        log.info("PUT /api/requests/{}/approve - Подтверждение заявки со скидкой: {}", id, discount);
        RequestApprovedDto approved = requestService.approveRequest(id, discount);
        return ResponseEntity.ok(approved);
    }

    @PutMapping("/{id}/confirm-price")
    public ResponseEntity<RequestStatusUpdateDto> confirmPrice(@PathVariable Long id, @RequestParam String email) {
        log.info("PUT /api/requests/{}/confirm-price - Подтверждение цены заявителем: {}", id, email);
        RequestStatusUpdateDto confirmed = requestService.confirmPrice(id, email);
        return ResponseEntity.ok(confirmed);
    }

    @PutMapping("/{id}/decline-request")
    public ResponseEntity<RequestStatusUpdateDto> declineRequest(@PathVariable Long id) {
        log.info("PUT /api/requests/{}/decline - Отклонение заявки", id);
        RequestStatusUpdateDto declined = requestService.declineRequest(id);
        return ResponseEntity.ok(declined);
    }

    @PutMapping("/{id}/complete-request")
    public ResponseEntity<RequestStatusUpdateDto> completeRequest(@PathVariable Long id) {
        log.info("PUT /api/requests/{}/complete - Завершение заявки", id);
        RequestStatusUpdateDto completed = requestService.completeRequest(id);
        return ResponseEntity.ok(completed);
    }

}
