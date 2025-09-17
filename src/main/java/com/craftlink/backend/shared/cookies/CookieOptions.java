package com.craftlink.backend.shared.cookies;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CookieOptions {

  String name;
  String value;
  long expirationTimeInSeconds;
  String domain;
  boolean isSecure;
  String path;
  boolean httpOnly;
  String sameSite;
}
