package com.craftlink.backend.category.adapter.out.persistance;


import com.craftlink.backend.jobRequest.adapter.port.out.persistence.JobRequestEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import com.craftlink.backend.shared.enums.LifecycleStatus;
import com.craftlink.backend.specialist.adapter.out.persistence.SpecialistEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private CategoryEntity category;

  @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
  private Set<JobRequestEntity> jobRequests = new HashSet<>();

  @ManyToMany(mappedBy = "offeredServices", fetch = FetchType.LAZY)
  private Set<SpecialistEntity> specialists = new HashSet<>();

  @Enumerated(EnumType.STRING)
  private LifecycleStatus status = LifecycleStatus.ACTIVE;
}
