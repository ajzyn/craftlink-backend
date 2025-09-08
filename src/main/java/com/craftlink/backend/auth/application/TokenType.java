package com.craftlink.backend.auth.application;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TokenType {
  REFRESH_TOKEN
}
