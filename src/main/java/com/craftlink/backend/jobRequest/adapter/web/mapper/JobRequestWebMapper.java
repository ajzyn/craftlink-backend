package com.craftlink.backend.jobRequest.adapter.web.mapper;

import com.craftlink.backend.jobRequest.adapter.web.dto.CreateJobRequestRequestDto;
import com.craftlink.backend.jobRequest.adapter.web.dto.CreateJobRequestResponseDto;
import com.craftlink.backend.jobRequest.application.dto.CreateJobRequestCommand;
import com.craftlink.backend.jobRequest.application.dto.CreateJobRequestResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobRequestWebMapper {

  CreateJobRequestCommand toCommand(
      CreateJobRequestRequestDto jobRequestRequestDto);

  CreateJobRequestResponseDto toResponse(CreateJobRequestResult jobRequestCommand);
}
