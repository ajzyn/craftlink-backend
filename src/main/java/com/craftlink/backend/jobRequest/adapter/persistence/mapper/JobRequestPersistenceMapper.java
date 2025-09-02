package com.craftlink.backend.jobRequest.adapter.persistence.mapper;

import com.craftlink.backend.client.entities.ClientEntity;
import com.craftlink.backend.jobRequest.adapter.persistence.JobRequestEntity;
import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.CalculatedDeadline;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.City;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Deadline;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Description;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.JobRequestId;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.PreferredDate;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.RequesterId;
import com.craftlink.backend.service.domain.model.ServiceId;
import com.craftlink.backend.service.entities.ServiceEntity;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobRequestPersistenceMapper {

  default JobRequestEntity toEntity(JobRequest d) {
    var e = new JobRequestEntity();

    var requester = new ClientEntity();
    requester.setId(d.getRequesterId().value());

    var service = new ServiceEntity();
    service.setId(d.getServiceId().value());

    e.setId(d.getId().value());
    e.setRequester(requester);
    e.setService(service);
    e.setStatus(d.getStatus());
    e.setDeadlineType(d.getDeadlineType());
    e.setDeadline(d.getDeadline().value());
    e.setDescription(d.getDescription().toString());
    e.setCity(d.getCity().toString());
    e.setDistrict(d.getDistrict());
    e.setPreferredDate(d.getPreferredDate().value());
    return e;
  }

  default JobRequest toDomain(JobRequestEntity e) {
    return JobRequest.rehydrate(
        new JobRequestId(e.getId()),
        new RequesterId(e.getRequester().getId()),
        new ServiceId(e.getService().getId()),
        e.getDeadlineType(),
        e.getStatus(),
        new CalculatedDeadline(Optional.ofNullable(e.getCalculatedDeadline())),
        new Deadline(e.getDeadline()),
        new Description(e.getDescription()),
        new City(e.getCity()),
        e.getDistrict(),
        new PreferredDate(e.getPreferredDate())
    );
  }
}
