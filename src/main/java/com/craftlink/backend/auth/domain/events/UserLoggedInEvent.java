package com.craftlink.backend.auth.domain.events;

import java.time.Instant;
import java.util.UUID;

public record UserLoggedInEvent(UUID userId, String email, String refreshToken, Instant occurredAt) {

}
