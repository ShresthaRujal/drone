package com.rujal.drones.domain;

import static com.rujal.drones.utils.Constants.Database.DRONE;

import com.rujal.drones.utils.Model;
import com.rujal.drones.utils.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity(name = DRONE)
public class Drone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
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
