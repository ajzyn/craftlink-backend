package com.craftlink.backend.category.adapter.in.web.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceSummaryResponseDto {

  private UUID id;
  private String name;
  private String slug;
}
