package com.craftlink.backend.config.security.utils;

import com.craftlink.backend.config.security.models.UserPrincipal;
import java.util.Arrays;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("authz")
@RequiredArgsConstructor
public class AuthorizationHelper {

    public boolean hasSpecialization(String code) {
        return getCurrentPrincipal()
            .map(user -> user.getOfferedServices().contains(code))
            .orElse(false);
    }

    public boolean hasAnySpecialization(String... codes) {
        return getCurrentPrincipal()
            .map(user -> {
                var offeredServices = user.getOfferedServices();
                return Arrays.stream(codes).anyMatch(offeredServices::contains);
            })
            .orElse(false);
    }

    private Optional<UserPrincipal> getCurrentPrincipal() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserPrincipal user)) {
            return Optional.empty();
        }

        return Optional.of(user);
    }
}
