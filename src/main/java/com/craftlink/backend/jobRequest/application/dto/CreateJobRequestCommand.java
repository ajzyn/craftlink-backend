package com.craftlink.backend.jobRequest.application.dto;

import com.craftlink.backend.jobRequest.domain.model.vo.DeadlineType;
import java.time.LocalDate;
import java.util.UUID;

public record CreateJobRequestCommand(
    String city,
    String district,
    DeadlineType deadlineType,
    LocalDate exactDate,
    UUID serviceId,
    String description
) {

}
