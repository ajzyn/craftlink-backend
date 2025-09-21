package com.craftlink.backend.category.adapter.in.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDetailsResponseDto extends ServiceSummaryResponseDto {

  private CategoryDetailsResponseDto category;
}
