package com.rujal.drones.domain;

import static com.rujal.drones.utils.Constants.Database.DRONE;
import static com.rujal.drones.utils.Constants.Database.JOIN_DRONE_ID;
import static com.rujal.drones.utils.Constants.Database.JOIN_DRONE_MEDICATION;
import static com.rujal.drones.utils.Constants.Database.JOIN_MEDICATION_ID;

import com.rujal.drones.utils.Model;
import com.rujal.drones.utils.State;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = DRONE)
public class Drone implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String serialNumber;
  private Model model;
  private int weightLimit;
  private BigDecimal batteryCapacity;
  @Enumerated(EnumType.STRING)
  private State state;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(name = JOIN_DRONE_MEDICATION,
      joinColumns = {@JoinColumn(name = JOIN_DRONE_ID)},
      inverseJoinColumns = {@JoinColumn(name = JOIN_MEDICATION_ID)}
  )
  @Fetch(FetchMode.SUBSELECT)
  private List<Medication> medications;

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

  public List<Medication> getMedications() {
    return medications;
  }

  public void setMedications(List<Medication> medications) {
    this.medications = medications;
  }
}
