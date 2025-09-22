package com.craftlink.backend.client.adapter.port.out.persistence.write;

import com.craftlink.backend.client.adapter.port.out.persistence.write.mapper.ClientPersistenceMapper;
import com.craftlink.backend.client.application.port.out.write.ClientRepository;
import com.craftlink.backend.client.domain.model.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientRepositoryHandler implements ClientRepository {

  private final ClientPersistenceMapper mapper;
  private final ClientRepositoryJpa jpa;

  @Override
  public void save(Client client) {
    var entity = mapper.toEntity(client);
    jpa.save(entity);
  }
}
