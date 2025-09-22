package com.craftlink.backend.category.application.port.out.write;

import com.craftlink.backend.category.domain.model.categoryImage.CategoryImage;
import com.craftlink.backend.category.domain.model.categoryImage.vo.CategoryImageId;
import java.util.Optional;

public interface CategoryImageRepository {

  void save(CategoryImage image);

  Optional<CategoryImage> findById(CategoryImageId id);
}
