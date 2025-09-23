package com.craftlink.backend.jobRequest.domain.model.jobRequest;

import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.City;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.Deadline;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.DeadlineType;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.Description;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.District;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.ExactDate;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.JobRequestId;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.JobRequestStatus;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.RequesterId;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.ServiceId;
import lombok.Getter;


@Getter
public final class JobRequest {

  private final JobRequestId id;
  private final RequesterId requesterId;
  private final ServiceId serviceId;
  private final DeadlineType deadlineType;
  private final Deadline deadline;
  private final Description description;
  private final City city;
  private final District district;
  private final ExactDate exactDate;
  private JobRequestStatus status = JobRequestStatus.ACTIVE;
  //TODO: add photos and consider if I want to allow to allow editing job requests or force user to recreate it

  private JobRequest(
      JobRequestId id, RequesterId requesterId,
      ServiceId serviceId, DeadlineType deadlineType, Deadline deadline,
      Description description,
      City city, District district, ExactDate exactDate) {

    this.id = id;
    this.requesterId = requesterId;
    this.serviceId = serviceId;
    this.deadlineType = deadlineType;
    this.deadline = deadline;
    this.description = description;
    this.city = city;
    this.district = district;
    this.exactDate = exactDate;
  }

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

    return new JobRequest(
        id, requesterId, serviceId, deadlineType,
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

  public void take() {
    this.status = JobRequestStatus.TAKEN;
  }

  public void complete() {
    this.status = JobRequestStatus.COMPLETED;
  }

  public void cancel() {
    this.status = JobRequestStatus.CANCELLED;
  }
}
