package com.craftlink.backend.category;

import com.craftlink.backend.category.dtos.CategoryDto;
import com.craftlink.backend.category.services.CategoryImageService;
import com.craftlink.backend.category.services.CategoryService;
import com.craftlink.backend.config.aws.dtos.PresignedUploadRequestDto;
import com.craftlink.backend.config.aws.dtos.PresignedUploadResponseDto;
import com.craftlink.backend.shared.dtos.TemporaryCredentialsResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryImageService categoryImageService;


    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(
        @RequestParam(defaultValue = "") String searchPhrase) {
        return ResponseEntity.ok(categoryService.getCategoriesWithAssignedServices());
    }

    //TODO: add authorization check
    @PostMapping("/{categoryId}/credentials")
    public TemporaryCredentialsResponse getCategoryCredentials(@PathVariable String categoryId) {
        return null;
    }

    @PostMapping("/image/upload-url")
    public ResponseEntity<PresignedUploadResponseDto> getPresignedUploadUrl(@Valid @RequestBody
    PresignedUploadRequestDto request) {
        return ResponseEntity.ok(categoryImageService.createUploadImageSession(request));
    }

    @PatchMapping("/image/{imageKey}/complate")
    public ResponseEntity<HttpStatus> markUploadImageAsCompleted(@PathVariable String imageKey) {
        categoryImageService.markUploadCompleted(imageKey);

        return ResponseEntity.noContent().build();
    }
}
