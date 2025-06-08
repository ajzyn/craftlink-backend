package com.craftlink.backend.auth.services;

import com.craftlink.backend.auth.dtos.LoginRequestDto;
import com.craftlink.backend.auth.dtos.RegisterRequestDto;
import com.craftlink.backend.security.models.UserPrincipal;
import com.craftlink.backend.security.services.AccessTokenService;
import com.craftlink.backend.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;
    private final UserService userService;

    public void registerClient(RegisterRequestDto registerRequestDto) {
        userService.registerClient(registerRequestDto);
    }

    public void registerSpecialist(RegisterRequestDto registerRequestDto) {
        userService.registerSpecialist(registerRequestDto);
    }

    public String login(LoginRequestDto loginRequestDto) {
        var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        var userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return accessTokenService.generateAccessToken(userPrincipal.getUsername());
    }
}
