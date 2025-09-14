package com.craftlink.backend.auth.domain.events;

import java.util.UUID;

public record UserLoggedOutEvent(String rawToken, UUID userId) {

}
