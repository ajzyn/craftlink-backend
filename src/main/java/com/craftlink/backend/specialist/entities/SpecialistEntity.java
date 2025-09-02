package com.craftlink.backend.specialist.entities;

import com.craftlink.backend.service.entities.ServiceEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import com.craftlink.backend.specialist.models.SpecialistType;
import com.craftlink.backend.user.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
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
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Enumerated(EnumType.STRING)
  private SpecialistType type;

  @OneToOne(mappedBy = "specialist", optional = false)
  private UserEntity user;

  @ManyToMany()
  @JoinTable(
      name = "specialists_services",
      joinColumns = @JoinColumn(name = "specialist_id"),
      inverseJoinColumns = @JoinColumn(name = "service_id")
  )
  private Set<ServiceEntity> offeredServices = new HashSet<>();
}
