package com.craftlink.backend.category.application.port.out.read;

import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import java.util.List;
import java.util.Optional;

public interface CategoryQueryRepository {

  List<ServiceEntity> findByNameContainingIgnoreCase(String searchPhrase);

  Optional<ServiceEntity> findBySlugAndStatus(String slug);
}
