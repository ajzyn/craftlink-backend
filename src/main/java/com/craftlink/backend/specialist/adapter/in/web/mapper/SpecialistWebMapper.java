package com.craftlink.backend.specialist.adapter.in.web.mapper;

import com.craftlink.backend.specialist.adapter.in.web.dto.UpdateSpecialistProfileRequestDto;
import com.craftlink.backend.specialist.application.port.in.command.updateUserProfile.UpdateUserProfileCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialistWebMapper {
  
  UpdateUserProfileCommand toCommand(UpdateSpecialistProfileRequestDto dto);
}
