package com.craftlink.backend.auth;

import com.craftlink.backend.auth.dtos.LoginRequestDto;
import com.craftlink.backend.auth.dtos.RegisterRequestDto;
import com.craftlink.backend.auth.services.AuthService;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register-client")
    public ResponseEntity<HttpStatus> registerClient(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        authService.registerClient(registerRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/register-specialist")
    public ResponseEntity<HttpStatus> registerSpecialist(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        authService.registerSpecialist(registerRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        var token = authService.login(loginRequestDto);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
