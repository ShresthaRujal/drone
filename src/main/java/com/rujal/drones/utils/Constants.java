package com.rujal.drones.utils;

public class Constants {

  private Constants() {
  }

  public static class Commons {

    private Commons() {
    }

    public static final String BASE_PACKAGE = "com.rujal";
    public static final String EMPTY = "";
    public static final String LETTERS_NUMBERS_DASH_UNDERSCORE = "([A-Za-z0-9\\-\\_]+)";
    public static final String MESSAGE_LETTERS_NUMBERS_DASH_UNDERSCORE = "Only Upper case letters, Number, '-', '_' are allowed";
    public static final String UPPER_CASE_LETTERS_NUMBERS_UNDERSCORE = "([A-Z0-9\\_]+)";
    public static final String MESSAGE_UPPER_CASE_LETTERS_NUMBERS_UNDERSCORE = "Only Upper case letters, Number, '_' are allowed";
    public static final String VALIDATION_MESSAGE = "VALIDATION MESSAGE";
  }

  public static class Field {
    private Field() {}
    public static final String CREATED_DATE = "createdDate";
  }

  public static class Database {

    private Database() {
    }

    public static final String DRONE = "drones";
    public static final String MEDICATION = "medication";
    public static final String HISTORY = "history";
    public static final String JOIN_DRONE_MEDICATION = "drone_medication";
    public static final String JOIN_DRONE_ID = "drone_id";
    public static final String JOIN_MEDICATION_ID = "medication_id";
    public static final String EVENT_DETAIL = "even_detail";
    public static final String EVENT_DETAIL_COL_DEFINITION = "even_detail[]";
    public static final String EVENT_DETAILS = "even_details";
  }
  
  public static class Path {
    private Path () {}
    public static final String ID = "id";
    public static final String PATH_PARAM_ID = "{id}";
    public static final String MEDICATION_BASE_URL = "medications";
    public static final String DRONE_BASE_URL = "drones";
    public static final String AVAILABLE_DRONES = "/all-idle-drones";
    public static final String ADD_MEDICATIONS = "/add-medications";
  }

  public static class ValidationMessages {

    private ValidationMessages() {
    }

    public static final String VALIDATION_SERIAL_NUMBER_TOO_LONG = "validation.serial.number.too.long";
    public static final String VALIDATION_WEIGHT_LIMIT_EXCEED = "validation.weight.limit.exceed";
  }
}
