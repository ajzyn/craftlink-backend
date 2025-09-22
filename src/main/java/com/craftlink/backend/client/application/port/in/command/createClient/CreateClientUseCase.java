package com.craftlink.backend.client.application.port.in.command.createClient;

import java.util.UUID;

public interface CreateClientUseCase {

  void handle(UUID userId);
}
