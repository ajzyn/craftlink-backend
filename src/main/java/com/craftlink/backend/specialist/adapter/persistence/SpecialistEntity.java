package com.craftlink.backend.specialist.adapter.persistence;

import com.craftlink.backend.auth.adapter.persistence.UserEntity;
import com.craftlink.backend.service.entities.ServiceEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "specialists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "offeredServices"})
public class SpecialistEntity extends BaseEntity {

  @Id
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SpecialistType type;

  @OneToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @ManyToMany()
  @JoinTable(
      name = "specialists_services",
      joinColumns = @JoinColumn(name = "specialist_id"),
      inverseJoinColumns = @JoinColumn(name = "service_id")
  )
  private Set<ServiceEntity> offeredServices = new HashSet<>();
}
