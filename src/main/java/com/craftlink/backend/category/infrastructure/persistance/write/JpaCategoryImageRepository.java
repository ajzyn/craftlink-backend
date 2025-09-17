package com.craftlink.backend.category.infrastructure.persistance.write;

import com.craftlink.backend.category.application.port.out.write.CategoryImageRepository;
import com.craftlink.backend.category.domain.model.categoryImage.CategoryImage;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ImageKey;
import com.craftlink.backend.category.infrastructure.persistance.mapper.CategoryImagePersistenceMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaCategoryImageRepository implements CategoryImageRepository {

  private final CategoryImageRepositorySpringData repository;
  private final CategoryImagePersistenceMapper mapper;

  @Override
  public void save(CategoryImage image) {
    var entity = mapper.toEntity(image);
    repository.save(entity);
  }

  @Override
  public Optional<CategoryImage> findByImageKey(ImageKey imageKey) {
    return repository.findByImageKey(imageKey.value()).map(mapper::toDomain);
  }
}
