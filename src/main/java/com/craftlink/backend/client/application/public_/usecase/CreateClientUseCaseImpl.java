package com.craftlink.backend.client.application.public_.usecase;

import com.craftlink.backend.client.domain.model.Client;
import com.craftlink.backend.client.domain.model.vo.UserId;
import com.craftlink.backend.client.domain.port.ClientRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateClientUseCaseImpl implements CreateClientUseCase {

  private final ClientRepository clientRepository;

  @Override
  public UUID createNewClient(UUID userId) {
    var client = Client.create(new UserId(userId));

    clientRepository.save(client);

    return client.getId().value();
  }
}