package com.craftlink.backend.category.entities;

import com.craftlink.backend.shared.entities.BaseEntity;
import com.craftlink.backend.shared.enums.ImageStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    @Column(nullable = false, unique = true)
    @Column(nullable = false)
    private String imageKey;

    @Column(nullable = false)
    private String originalFileName;

    @Enumerated(EnumType.STRING)
    private ImageStatus imageStatus = ImageStatus.UPLOADING;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long fileSize;

    private String userId;

    private Instant uploadCompletedAt;

    public void markAsCompleted() {
        imageStatus = ImageStatus.COMPLETED;
        uploadCompletedAt = Instant.now();
    }

    public void markAsFailed() {
        imageStatus = ImageStatus.FAILED;
        uploadCompletedAt = Instant.now();
    }

    public boolean isUploadingOrFailed() {
        return imageStatus == ImageStatus.FAILED || imageStatus == ImageStatus.UPLOADING;
    }
}
