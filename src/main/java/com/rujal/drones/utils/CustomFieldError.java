package com.rujal.drones.utils;

/**
 * Use in mapping Field and respective error message
 */
public class CustomFieldError {
  private String fieldName;
  private String message;

  public CustomFieldError(String fieldName, String message) {
    this.fieldName = fieldName;
    this.message = message;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
