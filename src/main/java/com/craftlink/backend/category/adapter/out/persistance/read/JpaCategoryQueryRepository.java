package com.craftlink.backend.category.adapter.out.persistance.read;

import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import com.craftlink.backend.category.application.port.out.read.CategoryQueryRepository;
import java.util.List;
import java.util.Optional;

public class JpaCategoryQueryRepository implements CategoryQueryRepository {

  @Override
  public List<ServiceEntity> findByNameContainingIgnoreCase(String searchPhrase) {
    return List.of();
  }

  @Override
  public Optional<ServiceEntity> findBySlugAndStatus(String slug) {
    return Optional.empty();
  }
}
