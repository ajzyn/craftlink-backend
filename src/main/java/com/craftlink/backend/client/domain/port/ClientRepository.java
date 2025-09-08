package com.craftlink.backend.client.domain.port;

import com.craftlink.backend.client.domain.model.Client;

public interface ClientRepository {

  void save(Client client);
}
