package com.craftlink.backend.jobRequest.adapter.port.in.web.mapper;

import com.craftlink.backend.jobRequest.adapter.port.in.web.dto.CreateJobRequestRequestDto;
import com.craftlink.backend.jobRequest.adapter.port.in.web.dto.CreateJobRequestResponseDto;
import com.craftlink.backend.jobRequest.application.port.in.command.createJobRequest.CreateJobRequestCommand;
import com.craftlink.backend.jobRequest.application.port.in.command.createJobRequest.CreateJobRequestResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobRequestWebMapper {

  CreateJobRequestCommand toCommand(
      CreateJobRequestRequestDto jobRequestRequestDto);

  CreateJobRequestResponseDto toResponse(CreateJobRequestResult jobRequestCommand);
}
