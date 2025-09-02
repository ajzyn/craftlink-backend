package com.craftlink.backend.jobRequest.application.mapper;

import com.craftlink.backend.jobRequest.application.dto.CreateJobRequestResult;
import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.CalculatedDeadline;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.City;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Deadline;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Description;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.JobRequestId;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.PreferredDate;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.RequesterId;
import com.craftlink.backend.service.domain.model.ServiceId;
import java.time.LocalDate;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface JobRequestDomainMapper {

  @Mapping(source = "id", target = "id", qualifiedByName = "mapJobRequestId")
//  @Mapping(source = "requesterId", target = "requesterId", qualifiedByName = "mapRequesterId")
//  @Mapping(source = "serviceId", target = "serviceId", qualifiedByName = "mapServiceId")
//  @Mapping(source = "status", target = "status")
//  @Mapping(source = "deadlineType", target = "deadlineType")
//  @Mapping(source = "calculatedDeadline", target = "calculatedDeadline", qualifiedByName = "mapCalculatedDeadline")
//  @Mapping(source = "deadline", target = "deadline", qualifiedByName = "mapDeadline")
  @Mapping(source = "description", target = "description", qualifiedByName = "mapDescription")
//  @Mapping(source = "city", target = "city", qualifiedByName = "mapCity")
//  @Mapping(source = "district", target = "district")
//  @Mapping(source = "preferredDate", target = "preferredDate", qualifiedByName = "mapPreferredDate")
  CreateJobRequestResult toResult(JobRequest jobRequest);

  @Named("mapJobRequestId")
  default UUID map(JobRequestId id) {
    return id != null ? id.value() : null;
  }

  @Named("mapRequesterId")
  default UUID map(RequesterId id) {
    return id != null ? id.value() : null;
  }

  @Named("mapServiceId")
  default UUID map(ServiceId id) {
    return id != null ? id.value() : null;
  }

  @Named("mapCalculatedDeadline")
  default LocalDate map(CalculatedDeadline deadline) {
    return deadline != null ? deadline.value().orElse(null) : null;
  }

  @Named("mapDeadline")
  default LocalDate map(Deadline deadline) {
    return deadline != null ? deadline.value() : null;
  }

  @Named("mapDescription")
  default String map(Description description) {
    return description != null ? description.value() : null;
  }

  @Named("mapCity")
  default String map(City city) {
    return city != null ? city.value() : null;
  }

  @Named("mapPreferredDate")
  default LocalDate map(PreferredDate date) {
    return date != null ? date.value() : null;
  }
}
