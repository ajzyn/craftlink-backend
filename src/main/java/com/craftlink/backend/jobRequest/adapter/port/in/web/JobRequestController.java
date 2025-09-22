package com.craftlink.backend.jobRequest.adapter.port.in.web;

import com.craftlink.backend.jobRequest.adapter.port.in.web.dto.CreateJobRequestRequestDto;
import com.craftlink.backend.jobRequest.adapter.port.in.web.dto.CreateJobRequestResponseDto;
import com.craftlink.backend.jobRequest.adapter.port.in.web.mapper.JobRequestWebMapper;
import com.craftlink.backend.jobRequest.application.port.in.command.createJobRequest.CreateJobRequestUseCase;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

  // TODO: check if the user is a client
  @PostMapping()
  public ResponseEntity<CreateJobRequestResponseDto> createServiceRequest(@Valid @RequestBody
  CreateJobRequestRequestDto requestDto) {
    var cmd = jobRequestWebMapper.toCommand(requestDto);
    var result = createJobRequestUseCase.handle(cmd);
    return ResponseEntity.status(HttpStatus.CREATED).body(jobRequestWebMapper.toResponse(result));
  }

  @GetMapping()
  public ResponseEntity<?> getAllServiceRequests() {
    return ResponseEntity.ok(Map.of("statis", "dziala"));
  }
}
