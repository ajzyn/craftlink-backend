package com.craftlink.backend.category.repositories;

import com.craftlink.backend.category.entities.CategoryImageEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryImageRepository extends JpaRepository<CategoryImageEntity, Integer> {

    Optional<CategoryImageEntity> findByImageKey(String imageKey);
}
