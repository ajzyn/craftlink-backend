package com.craftlink.backend.category.infrastructure.web.dto;

import com.craftlink.backend.service.dtos.ServiceBasicDto;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDetailsDto extends CategoryBasicDto {

  private Set<ServiceBasicDto> services = new HashSet<>();
}
