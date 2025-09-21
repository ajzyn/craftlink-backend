package com.craftlink.backend.category.adapter.in.web.mapper;

import com.craftlink.backend.category.adapter.in.web.dto.ServiceDetailsResponseDto;
import com.craftlink.backend.category.adapter.in.web.dto.ServiceSummaryResponseDto;
import com.craftlink.backend.category.application.port.in.query.getServiceDetails.ServiceDetailsView;
import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceWebMapper {

  List<ServiceSummaryResponseDto> toDto(List<ServiceSummaryView> services);

  ServiceDetailsResponseDto toDto(ServiceDetailsView service);
}
