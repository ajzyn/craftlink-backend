package com.craftlink.backend.jobRequest.application.service;

import com.craftlink.backend.jobRequest.application.mapper.JobRequestDomainMapper;
import com.craftlink.backend.jobRequest.application.port.usecase.createJobRequest.CreateJobRequestCommand;
import com.craftlink.backend.jobRequest.application.port.usecase.createJobRequest.CreateJobRequestResult;
import com.craftlink.backend.jobRequest.application.port.usecase.createJobRequest.CreateJobRequestUseCase;
import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.vo.City;
import com.craftlink.backend.jobRequest.domain.model.vo.DeadlineType;
import com.craftlink.backend.jobRequest.domain.model.vo.Description;
import com.craftlink.backend.jobRequest.domain.model.vo.District;
import com.craftlink.backend.jobRequest.domain.model.vo.ExactDate;
import com.craftlink.backend.jobRequest.domain.model.vo.RequesterId;
import com.craftlink.backend.jobRequest.domain.model.vo.ServiceId;
import com.craftlink.backend.jobRequest.domain.port.JobRequestRepository;
import com.craftlink.backend.shared.security.CurrentUserProvider;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class CreateJobRequestUseCaseImpl implements CreateJobRequestUseCase {

  private final CurrentUserProvider currentUserProvider;
  private final JobRequestRepository repo;
  private final JobRequestDomainMapper mapper;

  @Override
  public CreateJobRequestResult handle(CreateJobRequestCommand cmd) {
    var clientId = currentUserProvider.getCurrentUser().clientId();
    var currentDate = LocalDate.now();

    var deadline = cmd.deadlineType() == DeadlineType.EXACT_DATE ?
        cmd.deadlineType().calculateExact(cmd.exactDate(), currentDate)
        : cmd.deadlineType().calculate(currentDate);

    var jobRequest = JobRequest.create(
        new RequesterId(clientId),
        new ServiceId(cmd.serviceId()),
        cmd.deadlineType(),
        deadline,
        new Description(cmd.description()),
        new City(cmd.city()),
        new District(cmd.district()),
        new ExactDate(cmd.exactDate())
    );

    var savedJr = repo.save(jobRequest);
    return mapper.toResult(savedJr);
  }
}
