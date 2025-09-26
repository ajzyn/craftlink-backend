package com.craftlink.backend.specialist.adapter.out.persistence.mapper;

import com.craftlink.backend.auth.adapter.out.persistence.UserEntity;
import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import com.craftlink.backend.shared.domain.vo.UserId;
import com.craftlink.backend.specialist.adapter.out.persistence.SpecialistEntity;
import com.craftlink.backend.specialist.domain.model.Specialist;
import com.craftlink.backend.specialist.domain.model.vo.Bio;
import com.craftlink.backend.specialist.domain.model.vo.FullName;
import com.craftlink.backend.specialist.domain.model.vo.Location;
import com.craftlink.backend.specialist.domain.model.vo.PhoneNumber;
import com.craftlink.backend.specialist.domain.model.vo.ProfilePhotoUrl;
import com.craftlink.backend.specialist.domain.model.vo.ServiceId;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistId;
import com.craftlink.backend.specialist.domain.model.vo.Verified;
import com.craftlink.backend.specialist.domain.model.vo.YearsOfExperience;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SpecialistPersistenceMapper {

  public Specialist toDomain(SpecialistEntity e) {
    var serviceIds = e.getOfferedServices().stream()
        .map(se -> new ServiceId(se.getId()))
        .collect(Collectors.toSet());

    return Specialist.rehydrate(
        new SpecialistId(e.getId()),
        new UserId(e.getUser().getId()),
        e.getType(),
        serviceIds,
        new FullName(e.getFullName()),
        new PhoneNumber(e.getPhoneNumber()),
        new Location(e.getLocation()),
        new Bio(e.getBio()),
        new ProfilePhotoUrl(e.getProfilePhotoUrl()),
        new YearsOfExperience(e.getYearsOfExperience()),
        new Verified(e.isVerified())
    );
  }

  public SpecialistEntity toEntity(Specialist specialist) {
    var specialistEntity = new SpecialistEntity();

    var userEntity = new UserEntity();
    userEntity.setId(specialist.getUserId().value());

    specialistEntity.setUser(userEntity);
    specialistEntity.setId(specialist.getId().value());
    specialistEntity.setType(specialist.getSpecialistType());
    specialistEntity.setFullName(specialist.getFullName().value());
    specialistEntity.setPhoneNumber(specialist.getPhoneNumber().value());
    specialistEntity.setLocation(specialist.getLocation().value());
    specialistEntity.setBio(specialist.getBio().value());
    specialistEntity.setProfilePhotoUrl(specialist.getProfilePhotoUrl().value());
    specialistEntity.setYearsOfExperience(specialist.getYearsOfExperience().value());
    specialistEntity.setVerified(specialist.isVerified());

    var serviceEntities = specialist.getServiceIds().stream().map(s -> {
      var serviceEntity = new ServiceEntity();
      serviceEntity.setId(s.value());
      return serviceEntity;
    }).collect(Collectors.toSet());
    specialistEntity.setOfferedServices(serviceEntities);

    return specialistEntity;
  }


}
