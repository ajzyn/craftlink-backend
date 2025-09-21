package com.craftlink.backend.category.adapter.out.persistance.write;

import com.craftlink.backend.category.adapter.out.persistance.CategoryImageEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryImageRepositorySpringData extends JpaRepository<CategoryImageEntity, UUID> {

}
