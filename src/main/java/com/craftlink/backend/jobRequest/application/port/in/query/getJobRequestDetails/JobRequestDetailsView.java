package com.craftlink.backend.jobRequest.application.port.in.query.getJobRequestDetails;

import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.JobRequestStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record JobRequestDetailsView(UUID id, JobRequestStatus status, LocalDateTime createdAt,
                                    UUID requesterId, UUID serviceId) {

}
