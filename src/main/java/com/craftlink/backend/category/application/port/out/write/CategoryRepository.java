package com.craftlink.backend.category.application.port.out.write;

import com.craftlink.backend.category.domain.model.category.Category;
import java.util.Optional;

public interface CategoryRepository {

  void save(Category category);

  Optional<Category> findBySlug(String slug);
}
