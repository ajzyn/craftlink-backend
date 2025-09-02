package com.craftlink.backend.jobRequest.application.service;

import com.craftlink.backend.auth.application.port.CurrentUserProvider;
import com.craftlink.backend.jobRequest.application.dto.CreateJobRequestCommand;
import com.craftlink.backend.jobRequest.application.dto.CreateJobRequestResult;
import com.craftlink.backend.jobRequest.application.mapper.JobRequestDomainMapper;
import com.craftlink.backend.jobRequest.application.usecase.CreateJobRequestUseCase;
import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.City;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Deadline;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Description;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.PreferredDate;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.RequesterId;
import com.craftlink.backend.jobRequest.domain.port.JobRequestRepository;
import com.craftlink.backend.service.domain.model.ServiceId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateJobRequestService implements CreateJobRequestUseCase {

  private final CurrentUserProvider currentUserProvider;
  private final JobRequestRepository repo;
  private final JobRequestDomainMapper mapper;

  @Override
  public CreateJobRequestResult handle(CreateJobRequestCommand cmd) {
    var currentUserId = currentUserProvider.getCurrentUserId();

    var sr = JobRequest.create(
        new RequesterId(currentUserId),
        new ServiceId(cmd.serviceId()),
        cmd.deadlineType(),
        new Deadline(cmd.deadlineDate()),
        new Description(cmd.description()),
        new City(cmd.city()),
        cmd.district(),
        new PreferredDate(cmd.preferredDate())
    );

    var savedSr = repo.save(sr);
    return mapper.toResult(savedSr);
  }
}
