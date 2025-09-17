package com.craftlink.backend.category.infrastructure.persistance.write;

import com.craftlink.backend.category.infrastructure.persistance.CategoryImageEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryImageRepositorySpringData extends JpaRepository<CategoryImageEntity, UUID> {

  Optional<CategoryImageEntity> findByImageKey(String imageKey);
}
