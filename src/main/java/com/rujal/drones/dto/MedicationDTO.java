package com.rujal.drones.dto;

import static com.rujal.drones.utils.Constants.Commons.LETTERS_NUMBERS_DASH_UNDERSCORE;
import static com.rujal.drones.utils.Constants.Commons.UPPER_CASE_LETTERS_NUMBERS_UNDERSCORE;

import jakarta.validation.constraints.Pattern;

public class MedicationDTO {
  private Long id;
  /**
   * only letters, numbers, ‘-‘, ‘_’
   */
  @Pattern(regexp = LETTERS_NUMBERS_DASH_UNDERSCORE, message = "Only Upper case letters, Number, '-', '_' are allowed")
  private String name;
  private int weight;
  /**
   * only upper case letters, numbers, ‘_’
   */
  @Pattern(regexp = UPPER_CASE_LETTERS_NUMBERS_UNDERSCORE, message = "Only Upper case letters, Number, '_' are allowed")
  private String code;
  /**
   * holds image path of medication case
   */
  private String image;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
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
