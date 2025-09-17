package com.craftlink.backend.config.modelmapper;

import com.craftlink.backend.category.adapter.in.web.dto.CategoryBasicDto;
import com.craftlink.backend.category.adapter.in.web.dto.CategoryDetailsDto;
import com.craftlink.backend.category.adapter.out.persistance.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {


  @Bean
  public ModelMapper modelMapper() {
    var mapper = new ModelMapper();
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    configureCategoryMapper(mapper);

    mapper.validate();
    return mapper;
  }


  private void configureCategoryMapper(ModelMapper mapper) {

    mapper.createTypeMap(CategoryEntity.class, CategoryBasicDto.class)
        .addMapping(src -> src.getImage().getImageKey(), CategoryBasicDto::setImageKey);

    mapper.createTypeMap(CategoryEntity.class, CategoryDetailsDto.class)
        .addMapping(src -> src.getImage().getImageKey(), CategoryDetailsDto::setImageKey);
  }
}
