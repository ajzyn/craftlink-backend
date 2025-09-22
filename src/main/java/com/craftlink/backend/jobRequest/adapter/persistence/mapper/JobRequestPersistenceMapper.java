package com.craftlink.backend.jobRequest.adapter.persistence.mapper;

import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import com.craftlink.backend.client.adapter.port.out.persistence.ClientEntity;
import com.craftlink.backend.jobRequest.adapter.persistence.JobRequestEntity;
import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.vo.City;
import com.craftlink.backend.jobRequest.domain.model.vo.Deadline;
import com.craftlink.backend.jobRequest.domain.model.vo.Description;
import com.craftlink.backend.jobRequest.domain.model.vo.District;
import com.craftlink.backend.jobRequest.domain.model.vo.ExactDate;
import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestId;
import com.craftlink.backend.jobRequest.domain.model.vo.RequesterId;
import com.craftlink.backend.jobRequest.domain.model.vo.ServiceId;
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
