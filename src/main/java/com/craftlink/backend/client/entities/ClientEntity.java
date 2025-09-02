package com.craftlink.backend.client.entities;

import com.craftlink.backend.jobRequest.adapter.persistence.JobRequestEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import com.craftlink.backend.user.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
public class ClientEntity extends BaseEntity {


  @Id
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  private UUID id;

  @OneToOne(mappedBy = "client", optional = false)
  private UserEntity user;

  @OneToMany(mappedBy = "requester")
  private Set<JobRequestEntity> jobRequests;
}
