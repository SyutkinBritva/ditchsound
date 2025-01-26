package ru.ditchsound.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Request.RequestDto;
import ru.ditchsound.catalog.service.RequestServiceImpl;

@RestController
@RequestMapping("/releaseRequest")
@RequiredArgsConstructor
public class RequestController {

    private final RequestServiceImpl requestService;

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getRequest(@PathVariable("id") Long requestId) {
        return new ResponseEntity<>(requestService.findRequest(requestId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto){
            RequestDto resultRequest = requestService.createRequest(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultRequest);
    }

}
