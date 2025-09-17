package com.craftlink.backend.category.adapter.out.persistance.read;

import com.craftlink.backend.category.adapter.out.persistance.CategoryImageEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryImageQueryRepositorySpringData extends JpaRepository<CategoryImageEntity, Integer> {

  //TODO: projection
  Optional<CategoryImageEntity> findByImageKey(String imageKey);
}
