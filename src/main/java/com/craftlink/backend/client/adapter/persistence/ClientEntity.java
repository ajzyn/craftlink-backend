package com.craftlink.backend.client.adapter.persistence;

import com.craftlink.backend.auth.adapter.persistence.UserEntity;
import com.craftlink.backend.jobRequest.adapter.persistence.JobRequestEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity extends BaseEntity {

  @Id
  @Column(updatable = false, nullable = false)
  private UUID id;

  @OneToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @OneToMany(mappedBy = "requester")
  private Set<JobRequestEntity> jobRequests;
}
