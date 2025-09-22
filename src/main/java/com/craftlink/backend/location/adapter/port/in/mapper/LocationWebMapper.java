package com.craftlink.backend.location.adapter.port.in.mapper;

import com.craftlink.backend.location.adapter.port.in.dto.CityDto;
import com.craftlink.backend.location.application.port.in.query.shared.CityView;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationWebMapper {

  List<CityDto> toDto(List<CityView> cities);
}
