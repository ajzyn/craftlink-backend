package com.craftlink.backend.jobRequest.domain.model;

import com.craftlink.backend.jobRequest.domain.model.vo.City;
import com.craftlink.backend.jobRequest.domain.model.vo.Deadline;
import com.craftlink.backend.jobRequest.domain.model.vo.DeadlineType;
import com.craftlink.backend.jobRequest.domain.model.vo.Description;
import com.craftlink.backend.jobRequest.domain.model.vo.District;
import com.craftlink.backend.jobRequest.domain.model.vo.ExactDate;
import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestId;
import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestStatus;
import com.craftlink.backend.jobRequest.domain.model.vo.RequesterId;
import com.craftlink.backend.jobRequest.domain.model.vo.ServiceId;
import lombok.Getter;


@Getter
public final class JobRequest {

  private final JobRequestId id;
  private final RequesterId requesterId;
  private final ServiceId serviceId;
  private final JobRequestStatus status;
  private final DeadlineType deadlineType;
  private final Deadline deadline;
  private final Description description;
  private final City city;
  private final District district;
  private final ExactDate exactDate;

  private JobRequest(
      JobRequestId id, RequesterId requesterId,
      ServiceId serviceId, JobRequestStatus status, DeadlineType deadlineType, Deadline deadline,
      Description description,
      City city, District district, ExactDate exactDate) {

    this.id = id;
    this.requesterId = requesterId;
    this.serviceId = serviceId;
    this.status = status;
    this.deadlineType = deadlineType;
    this.deadline = deadline;
    this.description = description;
    this.city = city;
    this.district = district;
    this.exactDate = exactDate;
  }

  public static JobRequest create(
      RequesterId requesterId,
      ServiceId serviceId,
      DeadlineType deadlineType,
      Deadline deadline,
      Description description,
      City city,
      District district,
      ExactDate exactDate
  ) {
    JobRequestId id = JobRequestId.newId();
    JobRequestStatus status = JobRequestStatus.ACTIVE;

    return new JobRequest(
        id, requesterId, serviceId, status, deadlineType,
        deadline, description, city, district, exactDate
    );
  }

  public static JobRequest rehydrate(
      JobRequestId id,
      RequesterId requesterId,
      ServiceId serviceId,
      DeadlineType deadlineType,
      JobRequestStatus status,
      Deadline deadlineDate,
      Description description,
      City city,
      District district,
      ExactDate exactDate) {
    return new JobRequest(
        id, requesterId, serviceId, status, deadlineType,
        deadlineDate, description, city, district, exactDate
    );
  }
}
