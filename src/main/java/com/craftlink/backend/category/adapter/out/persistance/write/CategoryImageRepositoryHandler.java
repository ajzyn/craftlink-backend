package com.craftlink.backend.category.adapter.out.persistance.write;

import com.craftlink.backend.category.adapter.out.persistance.write.mapper.CategoryImagePersistenceMapper;
import com.craftlink.backend.category.application.port.out.write.CategoryImageRepository;
import com.craftlink.backend.category.domain.model.categoryImage.CategoryImage;
import com.craftlink.backend.category.domain.model.categoryImage.vo.CategoryImageId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryImageRepositoryHandler implements CategoryImageRepository {

  private final CategoryImageRepositorySpringData repository;

  @Override
  public void save(CategoryImage image) {
    var entity = CategoryImagePersistenceMapper.toEntity(image);
    repository.save(entity);
  }

  @Override
  public Optional<CategoryImage> findById(CategoryImageId categoryImageId) {
    return repository.findById(categoryImageId.value()).map(CategoryImagePersistenceMapper::toDomain);
  }
}
