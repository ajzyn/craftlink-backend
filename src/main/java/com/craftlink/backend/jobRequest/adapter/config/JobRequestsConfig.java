package com.craftlink.backend.jobRequest.adapter.config;

import com.craftlink.backend.auth.application.port.CurrentUserProvider;
import com.craftlink.backend.jobRequest.application.mapper.JobRequestDomainMapper;
import com.craftlink.backend.jobRequest.application.port.JobRequestRepository;
import com.craftlink.backend.jobRequest.application.service.CreateJobRequestService;
import com.craftlink.backend.jobRequest.application.usecase.CreateJobRequestUseCase;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRequestsConfig {

  @Bean
  JobRequestDomainMapper jobRequestDomainMapper() {
    return Mappers.getMapper(JobRequestDomainMapper.class);
  }

  @Bean
  CreateJobRequestUseCase createJobRequestUseCase(CurrentUserProvider currentUserProvider,
      JobRequestRepository jobRequestRepository, JobRequestDomainMapper jobRequestDomainMapper) {
    return new CreateJobRequestService(currentUserProvider, jobRequestRepository,
        jobRequestDomainMapper);
  }
}
