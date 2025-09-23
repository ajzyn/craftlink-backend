package com.craftlink.backend.auth.domain.events;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import com.craftlink.backend.shared.domain.event.DomainEvent;
import java.util.UUID;

public record UserRegisteredEvent(UUID userId,
                                  UserType type) implements DomainEvent {

}