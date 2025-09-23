package com.craftlink.backend.shared.adapter.port.out.event;

import com.craftlink.backend.shared.application.port.out.DomainEventPublisher;
import com.craftlink.backend.shared.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEventPublisher implements DomainEventPublisher {

  private final ApplicationEventPublisher publisher;

  @Override
  public void publish(DomainEvent event) {
    publisher.publishEvent(event);
  }
}
