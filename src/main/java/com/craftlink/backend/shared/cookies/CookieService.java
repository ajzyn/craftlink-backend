package com.craftlink.backend.shared.cookies;

import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CookieService {

    public String getCookie(HttpServletRequest request, String cookieName) {
        return Optional.ofNullable(request.getCookies())
            .flatMap(cookies ->
                Arrays.stream(cookies)
                    .filter(cookie -> cookieName.equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
            )
            .orElse(null);
    }

    public void setCookie(HttpServletResponse response, CookieOptions cookieOptions) {
        validateCookieOptions(cookieOptions);
        log.info("Cookie validated");

        var cookie = new Cookie(cookieOptions.getName(), cookieOptions.getValue());
        cookie.setPath(cookieOptions.getPath());
        cookie.setSecure(cookieOptions.isSecure());
        cookie.setDomain(cookieOptions.getDomain());
        cookie.setHttpOnly(cookieOptions.isHttpOnly());
        cookie.setMaxAge((int) cookieOptions.getExpirationTimeInSeconds());

        response.addCookie(cookie);
        log.info("Cookie added: name={}, value={}", cookieOptions.getName(), cookieOptions.getValue());
    }

    private void validateCookieOptions(CookieOptions options) {
        Map<String, Object> context = new HashMap<>();

        if (options == null) {
            context.put("cookieOptions", null);
        } else {
            if (options.getName() == null || options.getName().isBlank()) {
                context.put("name", options.getName());
            }
            if (options.getName() != null && !options.getName().matches("^[^\\s=;]+$")) {
                context.put("nameFormat", options.getName());
            }
            if (options.getExpirationTimeInSeconds() < 0) {
                context.put("expirationTimeInSeconds", options.getExpirationTimeInSeconds());
            }
        }

        if (!context.isEmpty()) {
            throw new SecurityException(
                ExceptionCode.CONFIGURATION_ERROR,
                "Invalid CookieOptions",
                context
            );
        }
    }
}
