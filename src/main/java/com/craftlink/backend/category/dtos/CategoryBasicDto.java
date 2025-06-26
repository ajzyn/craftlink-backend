package com.craftlink.backend.category.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryBasicDto {

    private Integer id;
    private String name;
    private String slug;
    private String iconName;
}
