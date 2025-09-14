package com.craftlink.backend.auth.adapter.acl;

import com.craftlink.backend.auth.application.port.ClientProfilePort;
import com.craftlink.backend.client.application.service.CreateClientUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientProfileAcl implements ClientProfilePort {

  private final CreateClientUseCase createClientUseCase;

  @Override
  public UUID createClientForUser(UUID userId) {
    return createClientUseCase.handle(userId);
  }
}
