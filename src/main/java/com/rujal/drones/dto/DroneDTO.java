package com.rujal.drones.dto;

import static com.rujal.drones.utils.Constants.ValidationMessages.VALIDATION_SERIAL_NUMBER_TOO_LONG;
import static com.rujal.drones.utils.Constants.ValidationMessages.VALIDATION_WEIGHT_LIMIT_EXCEED;

import com.rujal.drones.utils.Model;
import com.rujal.drones.utils.State;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.Range;

public class DroneDTO {

  private Long id;
  @Size(max = 100, message = VALIDATION_SERIAL_NUMBER_TOO_LONG)
  private String serialNumber;
  private Model model;
  @Range(min = 0, max = 500, message = VALIDATION_WEIGHT_LIMIT_EXCEED)
  private int weightLimit;
  private BigDecimal batteryCapacity;
  private State state;

  private List<MedicationDTO> medications = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Model getModel() {
    return model;
  }

  public void setModel(Model model) {
    this.model = model;
  }

  public int getWeightLimit() {
    return weightLimit;
  }

  public void setWeightLimit(int weightLimit) {
    this.weightLimit = weightLimit;
  }

  public BigDecimal getBatteryCapacity() {
    return batteryCapacity;
  }

  public void setBatteryCapacity(BigDecimal batteryCapacity) {
    this.batteryCapacity = batteryCapacity;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public List<MedicationDTO> getMedications() {
    return medications;
  }

  public void setMedications(List<MedicationDTO> medications) {
    this.medications = medications;
  }
}
