package com.craftlink.backend.jobRequest.domain.model.vo;

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
