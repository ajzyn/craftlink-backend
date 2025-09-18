package com.craftlink.backend.category.adapter.in.web.mapper;

import com.craftlink.backend.category.adapter.in.web.dto.CategoryDetailsDto;
import com.craftlink.backend.category.adapter.in.web.dto.CategorySummaryDto;
import com.craftlink.backend.category.adapter.in.web.dto.CreateCategoryImageUploadSessionRequestDto;
import com.craftlink.backend.category.adapter.in.web.dto.CreateCategoryImageUploadSessionResponseDto;
import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionCommand;
import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionResult;
import com.craftlink.backend.category.application.port.in.query.getCategoryDetails.CategoryDetailsView;
import com.craftlink.backend.category.application.port.in.query.getCategorySummaries.CategorySummaryView;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryWebMapper {

  CreateCategoryImageUploadSessionResponseDto toDto(CreateCategoryImageUploadSessionResult cmd);

  CreateCategoryImageUploadSessionCommand toCommand(CreateCategoryImageUploadSessionRequestDto dto);

  List<CategorySummaryDto> toDto(List<CategorySummaryView> category);

  CategoryDetailsDto toDto(CategoryDetailsView category);
}
