package com.craftlink.backend.category.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCategoryImageUploadSessionRequestDto(
    @Size(max = 255, message = "File name is too long")
    @Pattern(regexp = "^[\\w\\s\\-.]+\\.(jpg|jpeg|webp)$", flags = Pattern.Flag.CASE_INSENSITIVE)
    String fileName,
    @NotBlank String contentType,
    @NotNull Long fileSize) {

}
