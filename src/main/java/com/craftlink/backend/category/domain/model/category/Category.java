package com.craftlink.backend.category.domain.model.category;

import com.craftlink.backend.category.domain.model.category.vo.CategoryDescription;
import com.craftlink.backend.category.domain.model.category.vo.CategoryId;
import com.craftlink.backend.category.domain.model.category.vo.CategoryName;
import com.craftlink.backend.category.domain.model.category.vo.IconName;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ImageKey;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class Category {

  private final CategoryId id;
  private Set<Service> services = new HashSet<>();
  private CategoryName name;
  private CategoryDescription description;
  private IconName iconName;
  private ImageKey imageId;

  private Category(CategoryId id, CategoryName name, CategoryDescription description,
      IconName iconName, ImageKey imageId, Set<Service> services) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.iconName = iconName;
    this.imageId = imageId;
    this.services = new HashSet<>(services);
  }

  private Category(CategoryId id, CategoryName name, CategoryDescription description,
      IconName iconName, ImageKey imageId) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.iconName = iconName;
    this.imageId = imageId;
  }

  public static Category create(CategoryName name, CategoryDescription description,
      IconName iconName, ImageKey imageId, Set<Service> services) {
    return new Category(CategoryId.newId(), name, description, iconName, imageId, services);
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

  public void updateDetails(CategoryName name, CategoryDescription description, IconName iconName,
      ImageKey imageId) {
    this.name = name;
    this.description = description;
    this.iconName = iconName;
    this.imageId = imageId;
  }
}
