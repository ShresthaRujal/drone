package com.rujal.drones.domain;

import static com.rujal.drones.utils.Constants.ValidationMessages.VALIDATION_SERIAL_NUMBER_TOO_LONG;

import com.rujal.drones.utils.Model;
import com.rujal.drones.utils.State;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name = "drones")
public class Drone {

  @Id
  private Long id;
  @Column(unique = true)
  @Size(max = 100, message = VALIDATION_SERIAL_NUMBER_TOO_LONG)
  private String serialNumber;
  private Model model;
  private int weightLimit;
  private BigDecimal batteryCapacity;
  private State state;

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
}
