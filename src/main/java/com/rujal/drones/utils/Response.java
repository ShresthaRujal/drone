package com.rujal.drones.utils;

import static com.rujal.drones.utils.Constants.Commons.EMPTY;

public class Response {

  private Object data;
  private String message;
  private boolean success = false;

  private Response(Object data, String message) {
    this.data = data;
    this.message = message;
  }

  private Response(Object data, String message, boolean success) {
    this.data = data;
    this.message = message;
    this.success = success;
  }

  public static Response success(Object data, String message) {
    return new Response(data, message, true);
  }

  public static Response success(Object data) {
    return new Response(data, EMPTY, true);
  }

  public static Response failure(Object data, String message) {
    return new Response(data, message);
  }

  public static Response failure(String message) {
    return new Response(null, message);
  }

  public Object getData() {
    return data;
  }

  void setData(Object data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  void setMessage(String message) {
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}
