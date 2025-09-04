package com.craftlink.backend.jobRequest.adapter.persistence.mapper;

import com.craftlink.backend.client.entities.ClientEntity;
import com.craftlink.backend.jobRequest.adapter.persistence.JobRequestEntity;
import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.City;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Deadline;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Description;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.District;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.ExactDate;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.JobRequestId;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.RequesterId;
import com.craftlink.backend.service.domain.model.ServiceId;
import com.craftlink.backend.service.entities.ServiceEntity;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobRequestPersistenceMapper {

  default JobRequestEntity toEntity(JobRequest d) {
    var entity = new JobRequestEntity();

    var requester = new ClientEntity();
    requester.setId(d.getRequesterId().value());

    var service = new ServiceEntity();
    service.setId(d.getServiceId().value());

    entity.setId(d.getId().value());
    entity.setRequester(requester);
    entity.setService(service);
    entity.setStatus(d.getStatus());
    entity.setDeadlineType(d.getDeadlineType());
    entity.setDeadline(d.getDeadline().value().orElse(null));
    entity.setDescription(d.getDescription().value());
    entity.setCity(d.getCity().value());
    entity.setDistrict(d.getDistrict().value());
    entity.setExactDate(d.getExactDate().value());
    return entity;
  }

  default JobRequest toDomain(JobRequestEntity e) {
    return JobRequest.rehydrate(
        new JobRequestId(e.getId()),
        new RequesterId(e.getRequester().getId()),
        new ServiceId(e.getService().getId()),
        e.getDeadlineType(),
        e.getStatus(),
        new Deadline(Optional.ofNullable(e.getDeadline())),
        new Description(e.getDescription()),
        new City(e.getCity()),
        new District(e.getDistrict()),
        new ExactDate(e.getExactDate())
    );
  }
}
