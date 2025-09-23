package com.craftlink.backend.client.application.command;

import com.craftlink.backend.client.application.port.in.command.createClientProfile.CreateClientProfileCommand;
import com.craftlink.backend.client.application.port.in.command.createClientProfile.CreateClientProfileUseCase;
import com.craftlink.backend.client.application.port.out.write.ClientRepository;
import com.craftlink.backend.client.domain.model.client.Client;
import com.craftlink.backend.shared.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateClientProfileHandler implements CreateClientProfileUseCase {

  private final ClientRepository clientRepository;

  @Override
  public void handle(CreateClientProfileCommand cmd) {
    var client = Client.create(new UserId(cmd.userId()));

    clientRepository.save(client);
  }
}