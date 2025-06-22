package com.craftlink.backend.category.dtos;

import com.craftlink.backend.service.dtos.ServiceDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
public class CreateCategoryRequestDto {

    Set<ServiceDto> services = new HashSet<>();
    
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    private String slug;
    @NotBlank
    private String iconName;
    @NotBlank
    private String description;
    @NotBlank
    private String imageKey;
    @Positive
    private Long imageSize;
}
