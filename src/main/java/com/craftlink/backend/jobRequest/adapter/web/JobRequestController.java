package com.craftlink.backend.jobRequest.adapter.web;

import com.craftlink.backend.jobRequest.adapter.web.dto.CreateJobRequestRequestDto;
import com.craftlink.backend.jobRequest.adapter.web.mappers.JobRequestWebMapper;
import com.craftlink.backend.jobRequest.application.dto.CreateJobRequestResult;
import com.craftlink.backend.jobRequest.application.usecase.CreateJobRequestUseCase;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sec/job-requests")
public class JobRequestController {

  private final CreateJobRequestUseCase createJobRequestUseCase;
  private final JobRequestWebMapper jobRequestWebMapper;
//  private final GetJobRequestDetailsUseCase getJobRequestDetailsUseCase;

  // check if the user is a client
  // user should be auithenticated
  @PostMapping()
  public ResponseEntity<CreateJobRequestResult> createServiceRequest(@Valid @RequestBody
  CreateJobRequestRequestDto requestDto) {
    var cmd = jobRequestWebMapper.toCommand(requestDto);
    var result = createJobRequestUseCase.handle(cmd);
    return ResponseEntity.ok(result);
  }

  @GetMapping()
  public ResponseEntity<?> getAllServiceRequests() {
    return ResponseEntity.ok(Map.of("statis", "dziala"));
  }
}
