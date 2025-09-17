package com.craftlink.backend.category.adapter.in.web;

import com.craftlink.backend.category.adapter.in.web.dto.CategoryDetailsDto;
import com.craftlink.backend.category.adapter.in.web.dto.CategorySummaryDto;
import com.craftlink.backend.category.adapter.in.web.dto.CreateCategoryImageUploadSessionRequestDto;
import com.craftlink.backend.category.adapter.in.web.dto.CreateCategoryImageUploadSessionResponseDto;
import com.craftlink.backend.category.adapter.in.web.mapper.CategoryWebMapper;
import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionUseCase;
import com.craftlink.backend.category.application.port.in.command.markCategoryImageCompleted.MarkCategoryImageUploadCompletedCommand;
import com.craftlink.backend.category.application.port.in.command.markCategoryImageCompleted.MarkCategoryImageUploadCompletedUseCase;
import com.craftlink.backend.category.services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sec/categories")
public class CategoryController {

  private final CategoryService categoryService;
  private final CreateCategoryImageUploadSessionUseCase createUploadImageSession;
  private final MarkCategoryImageUploadCompletedUseCase markUploadCompleted;
  private final CategoryWebMapper categoryWebMapper;


  @GetMapping
  public ResponseEntity<List<CategorySummaryDto>> getCategories() {
    return ResponseEntity.ok(categoryService.getCategories());
  }

  @GetMapping("/{slug}")
  public ResponseEntity<CategoryDetailsDto> getCategoryWithServices(@PathVariable String slug) {
    return ResponseEntity.ok(categoryService.getCategoryDetailsBySlug(slug));
  }

  //TODO: add authorization check and create presignedULR to fetch image
  @PostMapping("/image/upload-url")
  public ResponseEntity<CreateCategoryImageUploadSessionResponseDto> getPresignedUploadUrl(@Valid @RequestBody
  CreateCategoryImageUploadSessionRequestDto request) {
    var cmd = categoryWebMapper.toCommand(request);
    return ResponseEntity.ok(categoryWebMapper.toDto(createUploadImageSession.handle(cmd)));
  }

  @PatchMapping("/image/{imageKey}/complete")
  public ResponseEntity<HttpStatus> markUploadImageAsCompleted(@PathVariable @NotBlank
  String imageKey) {
    markUploadCompleted.handle(new MarkCategoryImageUploadCompletedCommand(imageKey));

    return ResponseEntity.noContent().build();
  }
}
