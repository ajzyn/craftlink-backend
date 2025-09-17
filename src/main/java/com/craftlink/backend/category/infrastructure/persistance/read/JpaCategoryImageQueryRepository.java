package com.craftlink.backend.category.infrastructure.persistance.read;

import com.craftlink.backend.category.infrastructure.persistance.CategoryImageEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryImageQueryRepository extends JpaRepository<CategoryImageEntity, Integer> {

  Optional<CategoryImageEntity> findByImageKey(String imageKey);
}
