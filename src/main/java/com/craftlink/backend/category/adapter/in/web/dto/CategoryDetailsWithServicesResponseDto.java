package com.craftlink.backend.category.adapter.in.web.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDetailsWithServicesResponseDto extends CategoryDetailsResponseDto {


  private Set<ServiceSummaryResponseDto> services = new HashSet<>();
}
