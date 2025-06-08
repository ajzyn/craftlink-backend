package com.craftlink.backend.security.utils;

import com.craftlink.backend.security.models.UserPrincipal;
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
            .map(user -> user.getSpecializationCodes().contains(code))
            .orElse(false);
    }

    public boolean hasAnySpecialization(String... codes) {
        return getCurrentPrincipal()
            .map(user -> {
                var userSpecs = user.getSpecializationCodes();
                return Arrays.stream(codes).anyMatch(userSpecs::contains);
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
