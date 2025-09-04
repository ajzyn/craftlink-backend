package com.craftlink.backend.jobRequest.application.dto;

import com.craftlink.backend.jobRequest.domain.model.valueObjects.DeadlineType;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.JobRequestStatus;
import java.time.LocalDate;
import java.util.UUID;

public record CreateJobRequestResult(UUID id, String description, UUID serviceId, UUID requesterId,
                                     JobRequestStatus status, DeadlineType deadlineType, LocalDate deadline,
                                     String city, String district, LocalDate exactDate) {

}
