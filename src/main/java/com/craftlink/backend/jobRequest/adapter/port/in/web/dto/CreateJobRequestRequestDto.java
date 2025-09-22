package com.craftlink.backend.jobRequest.adapter.port.in.web.dto;

import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.DeadlineType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

public record CreateJobRequestRequestDto(@NotBlank String city,
                                         @NotBlank String district,
                                         @NotNull DeadlineType deadlineType,
                                         LocalDate deadlineDate,
                                         @NotNull
                                         LocalDate exactDate,
                                         @NotNull
                                         UUID serviceId,
                                         @NotBlank
                                         @Size(max = 500)
                                         String description) {

}
