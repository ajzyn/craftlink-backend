package com.craftlink.backend.client.domain.model.client;

import com.craftlink.backend.client.domain.model.client.vo.ClientId;
import com.craftlink.backend.client.domain.model.client.vo.JobRequestId;
import com.craftlink.backend.shared.domain.vo.UserId;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public final class Client {

  private final ClientId id;
  private final UserId userId;
  private Set<JobRequestId> jobRequestIds = new HashSet<>();

  private Client(ClientId id, UserId userId) {
    this.id = id;
    this.userId = userId;
  }

  private Client(ClientId id, UserId userId, Set<JobRequestId> jobRequestIds) {
    this.id = id;
    this.userId = userId;
    this.jobRequestIds = new HashSet<>(jobRequestIds);
  }

  public static Client create(UserId userId) {
    var id = ClientId.newId();
    return new Client(id, userId);
  }

  public static Client rehydrate(ClientId id, UserId userId, Set<JobRequestId> jobRequestIds) {
    return new Client(id, userId, jobRequestIds);
  }

  public void addJobRequestId(JobRequestId jobRequestId) {
    this.jobRequestIds.add(jobRequestId);
  }

  public void removeJobRequestId(JobRequestId jobRequestId) {
    this.jobRequestIds.remove(jobRequestId);
  }
}