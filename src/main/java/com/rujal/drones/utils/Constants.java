package com.rujal.drones.utils;

public class Constants {

  private Constants() {
  }

  public static class Commons {

    private Commons() {
    }

    public static final String LETTERS_NUMBERS_DASH_UNDERSCORE = "([A-Za-z0-9\\-\\_]+)";
    public static final String UPPER_CASE_LETTERS_NUMBERS_UNDERSCORE = "([A-Z0-9\\_]+)";
  }

  public static class Database {

    public Database() {
    }

    public static final String DRONE = "drones";
    public static final String MEDICATION = "medication";
  }

  public static class ValidationMessages {

    private ValidationMessages() {
    }

    public static final String VALIDATION_SERIAL_NUMBER_TOO_LONG = "validation.serial.number.too.long";
    public static final String VALIDATION_WEIGHT_LIMIT_EXCEED = "validation.weight.limit.exceed";
  }
}
