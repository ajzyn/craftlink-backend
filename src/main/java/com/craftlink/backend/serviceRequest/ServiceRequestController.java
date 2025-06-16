package com.craftlink.backend.serviceRequest;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sec/service-requests")
public class ServiceRequestController {

    @PostMapping()
    public ResponseEntity<?> createServiceRequest() {
        return null;
    }

    @GetMapping()
    public ResponseEntity<?> getAllServiceRequests() {
        return ResponseEntity.ok(Map.of("statis", "dziala"));
    }
}
