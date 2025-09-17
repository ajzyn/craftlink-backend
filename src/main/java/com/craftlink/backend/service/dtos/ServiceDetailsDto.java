package com.craftlink.backend.service.dtos;

import com.craftlink.backend.category.infrastructure.web.dto.CategoryBasicDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDetailsDto extends ServiceBasicDto {

  private CategoryBasicDto category;
}
