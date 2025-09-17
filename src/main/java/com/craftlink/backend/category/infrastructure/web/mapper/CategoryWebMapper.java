package com.craftlink.backend.category.infrastructure.web.mapper;

import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionCommand;
import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionResult;
import com.craftlink.backend.category.infrastructure.web.dto.CreateCategoryImageUploadSessionRequestDto;
import com.craftlink.backend.category.infrastructure.web.dto.CreateCategoryImageUploadSessionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryWebMapper {

  CreateCategoryImageUploadSessionResponseDto toDto(CreateCategoryImageUploadSessionResult cmd);

  CreateCategoryImageUploadSessionCommand toCommand(CreateCategoryImageUploadSessionRequestDto dto);
}
