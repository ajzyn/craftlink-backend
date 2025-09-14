package com.craftlink.backend.category;

import com.craftlink.backend.category.dtos.CategoryDetailsDto;
import com.craftlink.backend.category.dtos.CategorySummaryDto;
import com.craftlink.backend.category.services.CategoryImageService;
import com.craftlink.backend.category.services.CategoryService;
import com.craftlink.backend.config.aws.dtos.PresignedUploadRequestDto;
import com.craftlink.backend.config.aws.dtos.PresignedUploadResponseDto;
import jakarta.validation.Valid;
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
  private final CategoryImageService categoryImageService;


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
  public ResponseEntity<PresignedUploadResponseDto> getPresignedUploadUrl(@Valid @RequestBody
  PresignedUploadRequestDto request) {
    return ResponseEntity.ok(categoryImageService.createUploadImageSession(request));
  }

  @PatchMapping("/image/{imageKey}/complete")
  public ResponseEntity<HttpStatus> markUploadImageAsCompleted(@PathVariable String imageKey) {
    categoryImageService.markUploadCompleted(imageKey);

    return ResponseEntity.noContent().build();
  }
}
