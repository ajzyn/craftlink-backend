package com.craftlink.backend.category.dtos;

import com.craftlink.backend.service.dtos.ServiceDto;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Integer id;
    private String name;
    private String description;
    private String slug;
    private String iconName;
    private Set<ServiceDto> services = new HashSet<>();
    private String imageUrl;
}
