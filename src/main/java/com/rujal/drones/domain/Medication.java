package com.rujal.drones.domain;

import static com.rujal.drones.utils.Constants.Commons.LETTERS_NUMBERS_DASH_UNDERSCORE;
import static com.rujal.drones.utils.Constants.Commons.UPPER_CASE_LETTERS_NUMBERS_UNDERSCORE;
import static com.rujal.drones.utils.Constants.ValidationMessages.VALIDATION_WEIGHT_LIMIT_EXCEED;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "medications")
public class Medication {

  @Id
  private String id;
  /**
   * only letters, numbers, ‘-‘, ‘_’
   */
  @Pattern(regexp = LETTERS_NUMBERS_DASH_UNDERSCORE)
  private String name;
  @Size(max = 500, message = VALIDATION_WEIGHT_LIMIT_EXCEED)
  private String weight;
  /**
   * only upper case letters, numbers, ‘-‘, ‘_’
   */
  @Pattern(regexp = UPPER_CASE_LETTERS_NUMBERS_UNDERSCORE)
  private String code;
  /**
   * holds image path of medication case
   */
  private String image;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
