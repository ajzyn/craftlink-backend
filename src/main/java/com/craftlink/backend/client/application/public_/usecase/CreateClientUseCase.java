package com.craftlink.backend.client.application.public_.usecase;

import java.util.UUID;

public interface CreateClientUseCase {

  UUID createNewClient(UUID userId);
}
