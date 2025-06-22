package com.craftlink.backend.config.aws.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class PresignedUploadRequestDto {

    @NotBlank
    @Size(max = 255, message = "Nazwa pliku za długa")
    @Pattern(regexp = "^[\\w\\s\\-.]+\\.(jpg|jpeg|webp)$",
        flags = Pattern.Flag.CASE_INSENSITIVE)
    private String fileName;

    @NotBlank
    private String contentType;

    @NotNull
    @Min(value = 1)
    @Max(value = 1024 * 1024 * 10)
    private Long fileSize;
}
