package com.craftlink.backend.specialist.adapter.in.web.dto;

import com.craftlink.backend.specialist.domain.model.vo.SpecialistType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

public record UpdateSpecialistProfileRequestDto(

    @NotBlank()
    @Size(max = 100)
    String fullName,

    @NotBlank()
    @Size(max = 20)
    String phoneNumber,

    @NotBlank()
    String location,

    @Size(max = 1000)
    String bio,

    @NotNull()
    Set<UUID> serviceIds,

    @NotNull()
    SpecialistType specialistType
) {

}
