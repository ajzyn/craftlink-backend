package com.craftlink.backend.specialist.domain.model.vo;

public record Verified(boolean value) {

  public static Verified unverified() {
    return new Verified(false);
  }

  public Verified verify() {
    return new Verified(true);
  }
}
