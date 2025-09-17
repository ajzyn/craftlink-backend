package com.craftlink.backend.auth.adapter.out.external;

import com.craftlink.backend.auth.application.port.out.external.ClientProfilePort;
import com.craftlink.backend.client.application.service.CreateClientUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientProfileAcl implements ClientProfilePort {

  private final CreateClientUseCase createClientUseCase;

  @Override
  public void createClientForUser(UUID userId) {
    return createClientUseCase.handle(userId);
  }
}
