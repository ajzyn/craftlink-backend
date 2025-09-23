package com.craftlink.backend.specialist.domain.model;

import com.craftlink.backend.shared.domain.vo.UserId;
import com.craftlink.backend.specialist.domain.model.vo.Bio;
import com.craftlink.backend.specialist.domain.model.vo.FullName;
import com.craftlink.backend.specialist.domain.model.vo.Location;
import com.craftlink.backend.specialist.domain.model.vo.PhoneNumber;
import com.craftlink.backend.specialist.domain.model.vo.ProfilePhotoUrl;
import com.craftlink.backend.specialist.domain.model.vo.ServiceId;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistId;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistType;
import com.craftlink.backend.specialist.domain.model.vo.Verified;
import com.craftlink.backend.specialist.domain.model.vo.YearsOfExperience;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public final class Specialist {

  private final SpecialistId id;
  private final UserId userId;
  private Set<ServiceId> serviceIds;
  private SpecialistType specialistType;
  private FullName fullName;
  private PhoneNumber phoneNumber;
  private Location location;
  private Bio bio;
  private ProfilePhotoUrl profilePhotoUrl;
  private YearsOfExperience yearsOfExperience;
  private Verified verified = Verified.unverified();


  private Specialist(SpecialistId specialistId,
      UserId userId) {
    this.id = specialistId;
    this.userId = userId;
  }

  private Specialist(
      SpecialistId id,
      UserId userId,
      SpecialistType specialistType,
      Set<ServiceId> serviceIds,
      FullName fullName,
      PhoneNumber phoneNumber,
      Location location,
      Bio bio,
      ProfilePhotoUrl profilePhotoUrl,
      YearsOfExperience yearsOfExperience,
      Verified verified
  ) {
    this.id = id;
    this.userId = userId;
    this.specialistType = specialistType;
    this.serviceIds = new HashSet<>(serviceIds);
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.location = location;
    this.bio = bio;
    this.profilePhotoUrl = profilePhotoUrl;
    this.yearsOfExperience = yearsOfExperience;
    this.verified = verified == null ? Verified.unverified() : verified;
  }

  public static Specialist create(UserId userId) {
    return new Specialist(SpecialistId.newId(), userId);
  }

  public static Specialist rehydrate(
      SpecialistId id,
      UserId userId,
      SpecialistType specialistType,
      Set<ServiceId> serviceIds,
      FullName fullName,
      PhoneNumber phoneNumber,
      Location location,
      Bio bio,
      ProfilePhotoUrl profilePhotoUrl,
      YearsOfExperience yearsOfExperience,
      Verified verified
  ) {
    return new Specialist(
        id,
        userId,
        specialistType,
        serviceIds,
        fullName,
        phoneNumber,
        location,
        bio,
        profilePhotoUrl,
        yearsOfExperience,
        verified
    );
  }

  public void updateProfile(
      FullName fullName,
      PhoneNumber phoneNumber,
      Location location,
      Bio bio,
      Set<ServiceId> serviceIds,
      SpecialistType specialistType
  ) {
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.location = location;
    this.bio = bio;
    this.serviceIds = new HashSet<>(serviceIds);
    this.specialistType = specialistType;
  }

  public void updateProfilePhoto(ProfilePhotoUrl url) {
    this.profilePhotoUrl = url;
  }

  public void verify() {
    this.verified = this.verified.verify();
  }

  public boolean isVerified() {
    return this.verified.value();
  }
}
