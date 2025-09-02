package com.craftlink.backend.jobRequest.domain.model;

import com.craftlink.backend.jobRequest.domain.model.valueObjects.CalculatedDeadline;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.City;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Deadline;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.DeadlineType;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.Description;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.JobRequestId;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.JobRequestStatus;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.PreferredDate;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.RequesterId;
import com.craftlink.backend.service.domain.model.ServiceId;
import java.util.Optional;
import lombok.Getter;


@Getter
public final class JobRequest {

  private final JobRequestId id;
  private final RequesterId requesterId;
  private final ServiceId serviceId;
  private final JobRequestStatus status;
  private final DeadlineType deadlineType;
  private final CalculatedDeadline calculatedDeadline;
  private final Deadline deadline;
  private final Description description;
  private final City city;
  private final String district;
  private final PreferredDate preferredDate;

  private JobRequest(
      JobRequestId id, RequesterId requesterId,
      ServiceId serviceId, JobRequestStatus status, DeadlineType deadlineType,
      CalculatedDeadline calculatedDeadline, Deadline deadline, Description description,
      City city, String district, PreferredDate preferredDate) {

    this.id = id;
    this.requesterId = requesterId;
    this.serviceId = serviceId;
    this.status = status;
    this.deadlineType = deadlineType;
    this.calculatedDeadline = calculatedDeadline;
    this.deadline = deadline;
    this.description = description;
    this.city = city;
    this.district = district;
    this.preferredDate = preferredDate;
  }

  public static JobRequest create(
      RequesterId requesterId,
      ServiceId serviceId,
      DeadlineType deadlineType,
      Deadline deadlineDate,
      Description description,
      City city,
      String district,
      PreferredDate preferredDate
  ) {
    JobRequestId id = JobRequestId.newId();
    JobRequestStatus status = JobRequestStatus.ACTIVE;
    CalculatedDeadline calculated;

    if (deadlineType.isSupportsCalculation()) {
      calculated = deadlineType.calculate(deadlineDate);
    } else {
      calculated = new CalculatedDeadline(Optional.empty());
    }

    return new JobRequest(
        id, requesterId, serviceId, status, deadlineType,
        calculated, deadlineDate, description, city, district, preferredDate
    );
  }

  public static JobRequest rehydrate(
      JobRequestId id,
      RequesterId requesterId,
      ServiceId serviceId,
      DeadlineType deadlineType,
      JobRequestStatus status,
      CalculatedDeadline calculatedDeadline,
      Deadline deadlineDate,
      Description description,
      City city,
      String district,
      PreferredDate preferredDate) {
    return new JobRequest(
        id, requesterId, serviceId, status, deadlineType,
        calculatedDeadline, deadlineDate, description, city, district, preferredDate
    );
  }
}
