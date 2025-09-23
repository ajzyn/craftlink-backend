package com.craftlink.backend.specialist.adapter.in.event;

import com.craftlink.backend.auth.domain.events.UserRegisteredEvent;
import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import com.craftlink.backend.specialist.application.port.in.command.createSpecialistProfile.CreateSpecialistProfileCommand;
import com.craftlink.backend.specialist.application.port.in.command.createSpecialistProfile.CreateSpecialistProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserRegisteredEventListener {

  private final CreateSpecialistProfileUseCase createClientProfileUseCase;

  @EventListener
  void on(UserRegisteredEvent event) {
    if (event.type() == UserType.SPECIALIST) {
      createClientProfileUseCase.handle(
          new CreateSpecialistProfileCommand(event.userId())
      );
    }
  }
}