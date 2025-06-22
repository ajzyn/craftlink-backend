package com.craftlink.backend.service.dtos;

import com.craftlink.backend.category.dtos.CategoryDto;
import com.craftlink.backend.serviceRequest.dtos.ServiceRequestDto;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDto {

    private Integer id;
    private String name;
    private String slug;
    private CategoryDto category;
    private Set<ServiceRequestDto> serviceRequests = new HashSet<>();
}
