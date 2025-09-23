package com.craftlink.backend.auth.domain.events;

import com.craftlink.backend.shared.domain.event.DomainEvent;
import java.util.UUID;

public record UserLoggedOutEvent(String rawToken, UUID userId) implements DomainEvent {

}

