package com.craftlink.backend.category.domain.model.category;

import com.craftlink.backend.category.domain.model.category.vo.ServiceDescription;
import com.craftlink.backend.category.domain.model.category.vo.ServiceId;
import com.craftlink.backend.category.domain.model.category.vo.ServiceName;
import com.craftlink.backend.shared.enums.LifecycleStatus;
import lombok.Getter;

@Getter
public class Service {

  private final ServiceId id;
  private ServiceName name;
  private ServiceDescription description;
  private LifecycleStatus status;

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

  public void updateDetails(ServiceName name, ServiceDescription description) {
    this.name = name;
    this.description = description;
  }

  public void active() {
    this.status = LifecycleStatus.ACTIVE;
  }

  public void inactive() {
    this.status = LifecycleStatus.INACTIVE;
  }

  public void remove() {
    this.status = LifecycleStatus.DELETED;
  }
}