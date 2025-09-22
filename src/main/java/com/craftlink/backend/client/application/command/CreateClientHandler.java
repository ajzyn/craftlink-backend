package com.craftlink.backend.client.application.command;

import com.craftlink.backend.client.application.port.in.command.createClient.CreateClientUseCase;
import com.craftlink.backend.client.application.port.out.write.ClientRepository;
import com.craftlink.backend.client.domain.model.client.Client;
import com.craftlink.backend.shared.vo.UserId;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateClientHandler implements CreateClientUseCase {

  private final ClientRepository clientRepository;

  @Override
  public void handle(UUID userId) {
    var client = Client.create(new UserId(userId));

    clientRepository.save(client);
  }
}