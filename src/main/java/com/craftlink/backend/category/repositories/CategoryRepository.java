package com.craftlink.backend.category.repositories;

import com.craftlink.backend.category.entities.CategoryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query("""
        SELECT DISTINCT c FROM CategoryEntity c
        JOIN FETCH c.services
        """)
    List<CategoryEntity> findAllWithServices();
}
