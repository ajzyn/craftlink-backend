package com.craftlink.backend.specialist.adapter.out.persistence.mapper;

import com.craftlink.backend.auth.adapter.out.persistence.UserEntity;
import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import com.craftlink.backend.shared.domain.vo.UserId;
import com.craftlink.backend.specialist.adapter.out.persistence.SpecialistEntity;
import com.craftlink.backend.specialist.domain.model.Specialist;
import com.craftlink.backend.specialist.domain.model.vo.ServiceId;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistId;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialistPersistenceMapper {

  default SpecialistEntity toEntity(Specialist specialist) {
    var specialistEntity = new SpecialistEntity();

    var userEntity = new UserEntity();
    userEntity.setId(specialist.getUserId().value());
    specialistEntity.setUser(userEntity);

    specialistEntity.setId(specialist.getId().value());
    specialistEntity.setType(specialist.getSpecialistType());

    var serviceEntities = specialist.getServiceIds().stream().map(s -> {
      var serviceEntity = new ServiceEntity();
      serviceEntity.setId(s.value());
      return serviceEntity;
    }).collect(Collectors.toSet());
    specialistEntity.setOfferedServices(serviceEntities);

    return specialistEntity;
  }

  default Specialist toDomain(SpecialistEntity e) {
    var serviceIds = e.getOfferedServices().stream()
        .map(se -> new ServiceId(se.getId()))
        .collect(Collectors.toSet());

    return Specialist.rehydrate(
        new SpecialistId(e.getId()),
        new UserId(e.getUser().getId()),
        e.getType(),
        serviceIds
    );
  }
}
