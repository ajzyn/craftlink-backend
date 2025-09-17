package com.craftlink.backend.client.application.service;

import java.util.UUID;

public interface CreateClientUseCase {

  void handle(UUID userId);
}
