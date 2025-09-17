package com.craftlink.backend.category.adapter.in.web.mapper;

import com.craftlink.backend.category.adapter.in.web.dto.CreateCategoryImageUploadSessionRequestDto;
import com.craftlink.backend.category.adapter.in.web.dto.CreateCategoryImageUploadSessionResponseDto;
import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionCommand;
import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryWebMapper {

  CreateCategoryImageUploadSessionResponseDto toDto(CreateCategoryImageUploadSessionResult cmd);

  CreateCategoryImageUploadSessionCommand toCommand(CreateCategoryImageUploadSessionRequestDto dto);
}
