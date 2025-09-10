package com.craftlink.backend.client.adapter.persistence.write;

import com.craftlink.backend.client.adapter.persistence.mapper.ClientPersistenceMapper;
import com.craftlink.backend.client.domain.model.Client;
import com.craftlink.backend.client.domain.port.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

  private final ClientPersistenceMapper mapper;
  private final SpringDataClientRepositoryJpa jpa;

  @Override
  public void save(Client client) {
    var entity = mapper.toEntity(client);
    jpa.save(entity);
  }
}
