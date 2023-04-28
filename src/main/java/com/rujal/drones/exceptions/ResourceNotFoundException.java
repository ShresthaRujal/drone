package com.rujal.drones.exceptions;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException() {
    super();
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
