package com.rujal.drones.exceptions;

public class BackEndException extends RuntimeException{

  public BackEndException(Exception e) {
    super(e);
  }
}
