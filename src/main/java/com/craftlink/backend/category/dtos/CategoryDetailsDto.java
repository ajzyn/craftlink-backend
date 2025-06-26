package com.craftlink.backend.category.dtos;

import com.craftlink.backend.service.dtos.ServiceBasicDto;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDetailsDto extends CategoryBasicDto {

    private String imageKey;
    private String description;
    private Set<ServiceBasicDto> services = new HashSet<>();
}
