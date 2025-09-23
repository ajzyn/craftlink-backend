package com.craftlink.backend.client.adapter.port.in.event;

import com.craftlink.backend.auth.domain.events.UserRegisteredEvent;
import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import com.craftlink.backend.client.application.port.in.command.createClientProfile.CreateClientProfileCommand;
import com.craftlink.backend.client.application.port.in.command.createClientProfile.CreateClientProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisteredEventListener {

  private final CreateClientProfileUseCase createClientProfile;

  @EventListener
  public void on(UserRegisteredEvent event) {
    if (event.type() == UserType.CLIENT) {
      createClientProfile.handle(new CreateClientProfileCommand(event.userId()));
    }
  }
}
