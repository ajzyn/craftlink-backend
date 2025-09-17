package com.craftlink.backend.service.dtos;

import com.craftlink.backend.category.adapter.in.web.dto.CategoryBasicDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDetailsDto extends ServiceBasicDto {

  private CategoryBasicDto category;
}
