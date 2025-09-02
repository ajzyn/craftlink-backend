package com.craftlink.backend.service.dtos;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceBasicDto {

  private UUID id;
  private String name;
  private String slug;
}
