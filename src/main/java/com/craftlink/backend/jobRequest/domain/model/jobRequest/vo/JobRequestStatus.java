package com.craftlink.backend.jobRequest.domain.model.jobRequest.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum JobRequestStatus {

  ACTIVE,
  TAKEN,
  COMPLETED,
  CANCELLED
}
