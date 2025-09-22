package com.craftlink.backend.category.domain.model.category;

import com.craftlink.backend.category.domain.model.category.vo.ServiceDescription;
import com.craftlink.backend.category.domain.model.category.vo.ServiceId;
import com.craftlink.backend.category.domain.model.category.vo.ServiceName;
import com.craftlink.backend.shared.enums.LifecycleStatus;
import lombok.Getter;

@Getter
public class Service {

  private final ServiceId id;
  private final ServiceName name;
  private final ServiceDescription description;
  private final LifecycleStatus status;

  private Service(ServiceId id, ServiceName name, ServiceDescription description, LifecycleStatus status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.status = status;
  }

  static Service create(ServiceName name, ServiceDescription description, LifecycleStatus status) {
    return new Service(ServiceId.newId(), name, description, status);
  }

  public static Service rehydrate(ServiceId id, ServiceName name, ServiceDescription description,
      LifecycleStatus status) {
    return new Service(id, name, description, status);
  }

  public Service activate() {
    return new Service(id, name, description, LifecycleStatus.ACTIVE);
  }

  public Service deactivate() {
    return new Service(id, name, description, LifecycleStatus.INACTIVE);
  }
}