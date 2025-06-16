package com.craftlink.backend.auth.services;

import com.craftlink.backend.auth.dtos.LoginRequestDto;
import com.craftlink.backend.auth.dtos.LoginResponseDto;
import com.craftlink.backend.auth.dtos.RegisterRequestDto;
import com.craftlink.backend.auth.models.TokenType;
import com.craftlink.backend.auth.properties.RefreshTokenCookieProperties;
import com.craftlink.backend.security.models.UserPrincipal;
import com.craftlink.backend.security.services.AccessTokenService;
import com.craftlink.backend.shared.cookies.CookieOptions;
import com.craftlink.backend.shared.cookies.CookieService;
import com.craftlink.backend.user.dtos.UserDto;
import com.craftlink.backend.user.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final CookieService cookieService;
    private final RefreshTokenCookieProperties refreshTokenCookieProperties;
    private final ModelMapper modelMapper;

    public void registerClient(RegisterRequestDto registerRequestDto) {
        userService.registerClient(registerRequestDto);
    }

    public void registerSpecialist(RegisterRequestDto registerRequestDto) {
        userService.registerSpecialist(registerRequestDto);
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        var userPrincipal = (UserPrincipal) authentication.getPrincipal();
        var user = userService.getUserByEmail(userPrincipal.getUsername());

        var refreshToken = refreshTokenService.createRefreshToken(user);
        cookieService.setCookie(response, CookieOptions.builder()
            .name(TokenType.REFRESH.getName())
            .value(refreshToken.getToken())
            .isSecure(refreshTokenCookieProperties.isSecure())
            .httpOnly(refreshTokenCookieProperties.isHttpOnly())
            .path(refreshTokenCookieProperties.getPath())
            .domain(refreshTokenCookieProperties.getDomain())
            .expirationTimeInSeconds(refreshTokenCookieProperties.getExpirationTimeInSeconds())
            .build());

        var token = accessTokenService.generateAccessToken(userPrincipal.getUsername());
        return LoginResponseDto.builder()
            .token(token)
            .user(modelMapper.map(user, UserDto.class))
            .build();
    }
}
