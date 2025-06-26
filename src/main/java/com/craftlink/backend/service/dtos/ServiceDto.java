package com.craftlink.backend.service.dtos;

import com.craftlink.backend.category.dtos.CategoryBasicDto;
import com.craftlink.backend.serviceRequest.dtos.ServiceRequestDto;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDto extends ServiceBasicDto {

    private CategoryBasicDto category;
    private Set<ServiceRequestDto> serviceRequests = new HashSet<>();
}
