package com.craftlink.backend.category.adapter.out.persistance;

import com.craftlink.backend.category.domain.model.categoryImage.vo.Status;
import com.craftlink.backend.shared.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category_image")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryImageEntity extends BaseEntity {

  @Id
  @Column(nullable = false, unique = true, updatable = false)
  private String imageKey;

  @Column(nullable = false)
  private String originalFileName;

  @Enumerated(EnumType.STRING)
  private Status imageStatus;

  @Column(nullable = false)
  private String contentType;

  @Column(nullable = false)
  private Long fileSize;

  @Column(nullable = false)
  private UUID userId;

  private Instant uploadCompletedAt;
}
