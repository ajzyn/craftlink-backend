package com.craftlink.backend.category.entities;

import com.craftlink.backend.service.entities.ServiceEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Column(columnDefinition = "TEXT")
  private String description;

  private String name;
  @Column(unique = true)
  private String slug;
  private String iconName;


  @OneToMany(mappedBy = "category")
  private Set<ServiceEntity> services = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
  @JoinColumn(name = "image_id", nullable = false, unique = true)
  private CategoryImageEntity image;
}
