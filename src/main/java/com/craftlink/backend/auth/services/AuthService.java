package com.craftlink.backend.auth.services;

import com.craftlink.backend.auth.dtos.RegisterRequestDto;
import com.craftlink.backend.user.repositories.UserRepository;
import com.craftlink.backend.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;

    public void registerClient(RegisterRequestDto registerRequestDto) {
        userService.registerClient(registerRequestDto);
    }

    public void registerSpecialist(RegisterRequestDto registerRequestDto) {
        userService.registerSpecialist(registerRequestDto);
    }
}
