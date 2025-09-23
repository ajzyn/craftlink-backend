package com.craftlink.backend.shared.application.port.out;

import com.craftlink.backend.shared.domain.event.DomainEvent;

public interface DomainEventPublisher {

  void publish(DomainEvent event);
}
