package com.craftlink.backend.client.domain.model;

import com.craftlink.backend.client.domain.model.vo.ClientId;
import com.craftlink.backend.client.domain.model.vo.JobRequestId;
import com.craftlink.backend.shared.vo.UserId;
import java.util.Set;
import lombok.Getter;

@Getter
public final class Client {

  private final ClientId id;
  private final UserId userId;
  private final Set<JobRequestId> jobRequestIds;

  private Client(ClientId id, UserId userId, Set<JobRequestId> jobRequestIds) {
    this.id = id;
    this.userId = userId;
    this.jobRequestIds = jobRequestIds;
  }

  public static Client create(UserId userId) {
    var id = ClientId.newId();
    return new Client(id, userId, Set.of());
  }

  public static Client rehydrate(ClientId id, UserId userId, Set<JobRequestId> jobRequestIds) {
    return new Client(id, userId, jobRequestIds);
  }
}