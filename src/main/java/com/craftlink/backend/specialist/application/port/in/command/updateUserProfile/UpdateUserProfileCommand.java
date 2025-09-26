package com.craftlink.backend.specialist.application.port.in.command.updateUserProfile;

import com.craftlink.backend.specialist.domain.model.vo.SpecialistType;
import java.util.Set;
import java.util.UUID;

public record UpdateUserProfileCommand(
    String fullName,
    String phoneNumber,
    String location,
    String bio,
    Set<UUID> serviceIds,
    SpecialistType specialistType) {

}
