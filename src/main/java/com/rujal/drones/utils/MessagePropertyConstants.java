package com.rujal.drones.utils;

public enum MessagePropertyConstants {

  DRONE_NOT_FOUND("drone.not.found"),
  MEDICATION_NOT_FOUND("medication.not.found");

  private String value;

  MessagePropertyConstants(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
