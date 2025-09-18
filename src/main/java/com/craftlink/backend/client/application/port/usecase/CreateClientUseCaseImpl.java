package com.craftlink.backend.client.application.port.usecase;

import com.craftlink.backend.client.application.service.CreateClientUseCase;
import com.craftlink.backend.client.domain.model.Client;
import com.craftlink.backend.client.domain.port.ClientRepository;
import com.craftlink.backend.shared.vo.UserId;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateClientUseCaseImpl implements CreateClientUseCase {

  private final ClientRepository clientRepository;

  @Override
  public void handle(UUID userId) {
    var client = Client.create(new UserId(userId));

    clientRepository.save(client);
  }
}