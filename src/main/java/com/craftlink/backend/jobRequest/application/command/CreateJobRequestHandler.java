package com.craftlink.backend.jobRequest.application.command;

import com.craftlink.backend.jobRequest.application.command.mapper.JobRequestDomainMapper;
import com.craftlink.backend.jobRequest.application.port.in.command.createJobRequest.CreateJobRequestCommand;
import com.craftlink.backend.jobRequest.application.port.in.command.createJobRequest.CreateJobRequestResult;
import com.craftlink.backend.jobRequest.application.port.in.command.createJobRequest.CreateJobRequestUseCase;
import com.craftlink.backend.jobRequest.application.port.out.write.JobRequestRepository;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.City;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.DeadlineType;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.Description;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.District;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.ExactDate;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.RequesterId;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.ServiceId;
import com.craftlink.backend.shared.security.CurrentUserProvider;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class CreateJobRequestHandler implements CreateJobRequestUseCase {

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
