package com.craftlink.backend.category.domain.model.category;

import com.craftlink.backend.category.domain.model.category.vo.CategoryDescription;
import com.craftlink.backend.category.domain.model.category.vo.CategoryId;
import com.craftlink.backend.category.domain.model.category.vo.CategoryName;
import com.craftlink.backend.category.domain.model.category.vo.IconName;
import com.craftlink.backend.category.domain.model.category.vo.ServiceId;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ImageKey;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.Getter;

@Getter
public class Category {

  private final CategoryId id;
  private final CategoryName name;
  private final CategoryDescription description;
  private final IconName iconName;
  private final ImageKey imageId;

  private final Set<Service> services = new HashSet<>();

  private Category(CategoryId id, CategoryName name, CategoryDescription description,
      IconName iconName, ImageKey imageId) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.iconName = iconName;
    this.imageId = imageId;
  }

  public static Category create(CategoryName name, CategoryDescription description,
      IconName iconName, ImageKey imageId) {
    return new Category(CategoryId.newId(), name, description, iconName, imageId);
  }

  public static Category rehydrate(CategoryId id, CategoryName name, CategoryDescription description,
      IconName iconName, ImageKey imageId) {
    return new Category(id, name, description, iconName, imageId);
  }

  public void addService(Service service) {
    services.add(service);
  }


  public void removeService(Service service) {
    services.remove(service);
  }

  public Optional<Service> findService(ServiceId serviceId) {
    return services.stream().filter(s -> s.getId().equals(serviceId)).findFirst();
  }
}
