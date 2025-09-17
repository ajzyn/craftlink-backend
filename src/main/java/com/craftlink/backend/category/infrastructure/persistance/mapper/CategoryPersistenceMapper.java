package com.craftlink.backend.category.infrastructure.persistance.mapper;

import com.craftlink.backend.category.domain.model.category.Category;
import com.craftlink.backend.category.domain.model.category.Service;
import com.craftlink.backend.category.domain.model.category.vo.CategoryDescription;
import com.craftlink.backend.category.domain.model.category.vo.CategoryId;
import com.craftlink.backend.category.domain.model.category.vo.CategoryName;
import com.craftlink.backend.category.domain.model.category.vo.IconName;
import com.craftlink.backend.category.domain.model.category.vo.ServiceDescription;
import com.craftlink.backend.category.domain.model.category.vo.ServiceId;
import com.craftlink.backend.category.domain.model.category.vo.ServiceName;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ImageKey;
import com.craftlink.backend.category.infrastructure.persistance.CategoryEntity;
import com.craftlink.backend.category.infrastructure.persistance.ServiceEntity;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryPersistenceMapper {

  public static Category toDomain(CategoryEntity e) {
    var category = Category.rehydrate(
        new CategoryId(e.getId()),
        new CategoryName(e.getName()),
        new CategoryDescription(e.getDescription()),
        new IconName(e.getIconName()),
        new ImageKey(e.getImage().getImageKey())
    );

    e.getServices().forEach(s ->
        category.addService(Service.rehydrate(
            new ServiceId(s.getId()),
            new ServiceName(s.getName()),
            new ServiceDescription(s.getDescription()),
            s.getStatus()
        ))
    );

    return category;
  }

  public static CategoryEntity toEntity(Category d, String categorySlug, java.util.Map<Service, String> serviceSlugs) {
    var entity = new CategoryEntity();
    entity.setId(d.getId().value());
    entity.setName(d.getName().value());
    entity.setDescription(d.getDescription().value());
    entity.setIconName(d.getIconName().value());
    entity.setSlug(categorySlug);

    entity.setServices(
        d.getServices().stream().map(s -> {
          var se = new ServiceEntity();
          se.setId(s.getId().value());
          se.setName(s.getName().value());
          se.setDescription(s.getDescription().value());
          se.setSlug(serviceSlugs.get(s));
          se.setStatus(s.getStatus());
          se.setCategory(entity);
          return se;
        }).collect(Collectors.toSet())
    );

    return entity;
  }
}