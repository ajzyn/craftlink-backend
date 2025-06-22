package com.craftlink.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sec/services")
public class ServiceController {

    @GetMapping
    public ResponseEntity<?> getAllServices() {
        return null;
    }
}
