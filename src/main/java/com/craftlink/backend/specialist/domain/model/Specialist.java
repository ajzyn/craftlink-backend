package com.craftlink.backend.specialist.domain.model;

import com.craftlink.backend.shared.domain.model.vo.AggregateRoot;
import com.craftlink.backend.specialist.domain.model.vo.ServiceId;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistId;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistType;
import com.craftlink.backend.specialist.domain.model.vo.UserId;
import java.util.Set;
import lombok.Getter;

@Getter
public final class Specialist extends AggregateRoot {

  private final SpecialistId id;
  private final Set<ServiceId> serviceIds;
  private final SpecialistType specialistType; //TODO: handle it properly
  private final UserId userId;

  private Specialist(SpecialistId specialistId, Set<ServiceId> serviceIds, SpecialistType specialistType,
      UserId userId) {
    this.id = specialistId;
    this.serviceIds = serviceIds;
    this.specialistType = specialistType;
    this.userId = userId;
  }

  public static Specialist create(UserId userId) {
    return new Specialist(SpecialistId.newId(), Set.of(), SpecialistType.INDIVIDUAL, userId);
  }


  public static Specialist rehydrate(
      SpecialistId id, UserId userId, SpecialistType specialistType,
      Set<ServiceId> serviceIds) {
    return new Specialist(id, serviceIds, specialistType, userId);
  }
}
