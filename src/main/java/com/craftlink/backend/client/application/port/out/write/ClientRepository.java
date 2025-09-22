package com.craftlink.backend.client.application.port.out.write;

import com.craftlink.backend.client.domain.model.client.Client;

public interface ClientRepository {

  void save(Client client);
}
