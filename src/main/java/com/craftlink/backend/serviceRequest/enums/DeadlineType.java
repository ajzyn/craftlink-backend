package com.craftlink.backend.serviceRequest.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum DeadlineType {

  ASAP,
  WITHIN_5_DAYS,
  WITHIN_2_WEEKS,
  ADJUST,
  EXACT_DATE
}
