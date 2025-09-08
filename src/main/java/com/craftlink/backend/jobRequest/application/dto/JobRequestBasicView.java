package com.craftlink.backend.jobRequest.application.dto;

import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestStatus;
import java.time.LocalDateTime;
import java.util.UUID;


public record JobRequestBasicView(UUID id, JobRequestStatus status, LocalDateTime createdAt,
                                  UUID requesterId, UUID serviceId) {

}