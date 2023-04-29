package com.rujal.drones.utils;

import static com.rujal.drones.utils.Model.LIGHT;
import static com.rujal.drones.utils.State.IDLE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.rujal.drones.domain.Drone;
import com.rujal.drones.domain.Medication;
import com.rujal.drones.dto.DroneDTO;
import com.rujal.drones.dto.MedicationDTO;
import java.math.BigDecimal;

public abstract class AppDataTest {

  public static final Long ID = 1L;
  public static final Long ID_2 = 2L;
  public static final String MEDICATION_CODE = "CODE_MED_1";
  public static final String MEDICATION_CODE_2 = "CODE_MED_2";
  public static final String MEDICATION_IMAGE = "path://";
  public static final String MEDICATION_NAME = "MED_NAME";
  public static final int MEDICATION_WEIGHT = 100;
  public static final String SERIAL_NUMBER = "SERIAL_1";
  public static final String SERIAL_NUMBER_2 = "SERIAL_2";
  public static final BigDecimal BATTERY = new BigDecimal(100);
  public static final int WEIGHT_LIMIT = 500;

  public MedicationDTO mockMedicationDTO(Long id) {
    MedicationDTO medicationDTO = mock(MedicationDTO.class);
    given(medicationDTO.getId()).willReturn(id);
    return medicationDTO;
  }

  public DroneDTO mockDroneDTO(Long id) {
    DroneDTO droneDTO = mock(DroneDTO.class);
    given(droneDTO.getId()).willReturn(id);
    return droneDTO;
  }

  public static MedicationDTO createMedicationDTO(Long id) {
    MedicationDTO medicationDTO = new MedicationDTO();
    medicationDTO.setId(id);
    medicationDTO.setCode(MEDICATION_CODE);
    medicationDTO.setName(MEDICATION_NAME);
    medicationDTO.setWeight(MEDICATION_WEIGHT);
    return medicationDTO;
  }

  public Medication mockMedication(Long id) {
    Medication medication = mock(Medication.class);
    given(medication.getId()).willReturn(id);
    given(medication.getCode()).willReturn(MEDICATION_CODE);
    given(medication.getImage()).willReturn(MEDICATION_IMAGE);
    given(medication.getName()).willReturn(MEDICATION_NAME);
    given(medication.getWeight()).willReturn(MEDICATION_WEIGHT);
    return medication;
  }

  public Drone mockDrone(Long id) {
    Drone drone = mock(Drone.class);
    given(drone.getId()).willReturn(id);
    given(drone.getState()).willReturn(IDLE);
    given(drone.getBatteryCapacity()).willReturn(BATTERY);
    given(drone.getSerialNumber()).willReturn(SERIAL_NUMBER);
    given(drone.getWeightLimit()).willReturn(WEIGHT_LIMIT);
    given(drone.getModel()).willReturn(LIGHT);
    return drone;
  }

  public DroneDTO createDroneDTO(Long id) {
    DroneDTO droneDTO = new DroneDTO();
    droneDTO.setId(id);
    droneDTO.setState(IDLE);
    droneDTO.setBatteryCapacity(BATTERY);
    droneDTO.setSerialNumber(SERIAL_NUMBER);
    droneDTO.setWeightLimit(WEIGHT_LIMIT);
    droneDTO.setModel(LIGHT);
    return droneDTO;
  }
}
