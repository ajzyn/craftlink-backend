package com.craftlink.backend.specialist.application.port.in.query.shared;

import com.craftlink.backend.specialist.domain.model.vo.SpecialistType;
import java.util.Set;
import java.util.UUID;

public record SpecialistProfileView(
    UUID id,
    String fullName,
    String phoneNumber,
    String location,
    String bio,
    String profilePhotoUrl,
    Integer yearsOfExperience,
    boolean verified,
    Set<UUID> serviceIds,
    SpecialistType specialistType
) {

}
