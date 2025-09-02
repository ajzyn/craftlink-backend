package com.craftlink.backend.service.entities;


import com.craftlink.backend.category.entities.CategoryEntity;
import com.craftlink.backend.jobRequest.adapter.persistence.JobRequestEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import com.craftlink.backend.shared.enums.EntityStatus;
import com.craftlink.backend.specialist.entities.SpecialistEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"jobRequests", "category", "specialists"})
public class ServiceEntity extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  private UUID id;

  private String name;
  private String description;

  @Column(unique = true)
  private String slug;

  @ManyToOne(optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private CategoryEntity category;

  @OneToMany(mappedBy = "service")
  private Set<JobRequestEntity> jobRequests = new HashSet<>();

  @ManyToMany(mappedBy = "offeredServices")
  private Set<SpecialistEntity> specialists = new HashSet<>();

  @Enumerated(EnumType.STRING)
  private EntityStatus active = EntityStatus.ACTIVE;
}
