package com.rujal.drones.utils;

public enum MessagePropertyConstants {

  DRONE_NOT_FOUND("drone.not.found"),
  DRONE_NOT_AVAILABLE_NOW("drone.not.available.now"),
  MEDICATION_NOT_FOUND("medication.not.found"),
  DELETED_MEDICATION("delete.medication.record"),
  DELETED_DRONE("delete.drone.record"),
  FIELD_ERROR("field.error");

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
