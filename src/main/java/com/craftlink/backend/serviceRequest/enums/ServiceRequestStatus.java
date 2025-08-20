package com.craftlink.backend.serviceRequest.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ServiceRequestStatus {

  ACTIVE,
  TAKEN,
  COMPLETED,
  CANCELLED
}
