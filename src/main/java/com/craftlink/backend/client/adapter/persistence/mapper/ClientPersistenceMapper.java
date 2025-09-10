package com.craftlink.backend.client.adapter.persistence.mapper;

import com.craftlink.backend.auth.adapter.persistence.UserEntity;
import com.craftlink.backend.client.adapter.persistence.ClientEntity;
import com.craftlink.backend.client.domain.model.Client;
import com.craftlink.backend.client.domain.model.vo.ClientId;
import com.craftlink.backend.client.domain.model.vo.JobRequestId;
import com.craftlink.backend.client.domain.model.vo.UserId;
import com.craftlink.backend.jobRequest.adapter.persistence.JobRequestEntity;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientPersistenceMapper {

  default ClientEntity toEntity(Client client) {
    var clientEntity = new ClientEntity();

    var userEntity = new UserEntity();
    userEntity.setId(client.getUserId().value());
    clientEntity.setUser(userEntity);

    var jobRequestEntities = client.getJobRequestIds().stream().map(jr -> {
      var jobRequestEntity = new JobRequestEntity();
      jobRequestEntity.setId(jr.value());
      return jobRequestEntity;
    }).collect(Collectors.toSet());

    clientEntity.setJobRequests(jobRequestEntities);
    clientEntity.setId(client.getId().value());
    return clientEntity;
  }

  default Client toDomain(ClientEntity clientEntity) {
    var jobRequestIds = clientEntity.getJobRequests()
        .stream()
        .map(jr -> new JobRequestId(jr.getId()))
        .collect(Collectors.toSet());

    return Client.rehydrate(
        new ClientId(clientEntity.getId()),
        new UserId(clientEntity.getUser().getId()),
        jobRequestIds
    );
  }
}
