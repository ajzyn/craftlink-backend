package com.craftlink.backend.jobRequest.adapter.web.dto;

import com.craftlink.backend.jobRequest.domain.model.valueObjects.DeadlineType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

public record CreateJobRequestRequestDto(@NotBlank String city,
                                         @NotBlank String district,
                                         @NotNull DeadlineType deadlineType,
                                         LocalDate deadline,
                                         @NotNull
                                         LocalDate preferredDate,
                                         @NotNull
                                         UUID serviceId,
                                         @NotBlank
                                         @Size(max = 500)
                                         String description) {

}
