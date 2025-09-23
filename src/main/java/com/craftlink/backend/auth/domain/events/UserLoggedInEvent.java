package com.craftlink.backend.auth.domain.events;

import com.craftlink.backend.shared.domain.event.DomainEvent;
import java.time.Instant;
import java.util.UUID;

public record UserLoggedInEvent(UUID userId, String email, String refreshToken, Instant occurredAt) implements
    DomainEvent {

}