package com.craftlink.backend.jobRequest.application.mapper;

import com.craftlink.backend.jobRequest.application.dto.CreateJobRequestResult;
import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.vo.City;
import com.craftlink.backend.jobRequest.domain.model.vo.Deadline;
import com.craftlink.backend.jobRequest.domain.model.vo.Description;
import com.craftlink.backend.jobRequest.domain.model.vo.District;
import com.craftlink.backend.jobRequest.domain.model.vo.ExactDate;
import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestId;
import com.craftlink.backend.jobRequest.domain.model.vo.RequesterId;
import com.craftlink.backend.service.domain.model.ServiceId;
import java.time.LocalDate;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface JobRequestDomainMapper {

  @Mapping(source = "id", target = "id", qualifiedByName = "mapJobRequestId")
  @Mapping(source = "serviceId", target = "serviceId", qualifiedByName = "mapServiceId")
  @Mapping(source = "requesterId", target = "requesterId", qualifiedByName = "mapRequesterId")
  @Mapping(source = "deadline", target = "deadline", qualifiedByName = "mapDeadline")
  @Mapping(source = "description", target = "description", qualifiedByName = "mapDescription")
  @Mapping(source = "city", target = "city", qualifiedByName = "mapCity")
  @Mapping(source = "exactDate", target = "exactDate", qualifiedByName = "mapExactDate")
  @Mapping(source = "district", target = "district", qualifiedByName = "mapDistrict")
  CreateJobRequestResult toResult(JobRequest jobRequest);

  @Named("mapJobRequestId")
  default UUID map(JobRequestId id) {
    return id.value();
  }

  @Named("mapRequesterId")
  default UUID map(RequesterId id) {
    return id.value();
  }

  @Named("mapServiceId")
  default UUID map(ServiceId id) {
    return id.value();
  }

  @Named("mapExactDate")
  default LocalDate map(ExactDate exactDate) {
    return exactDate != null ? exactDate.value() : null;
  }

  @Named("mapDeadline")
  default LocalDate map(Deadline deadline) {
    return deadline != null ? deadline.value().orElse(null) : null;
  }

  @Named("mapDescription")
  default String map(Description description) {
    return description.value();
  }

  @Named("mapCity")
  default String map(City city) {
    return city.value();
  }

  @Named("mapDistrict")
  default String map(District district) {
    return district != null ? district.value() : null;
  }
}
