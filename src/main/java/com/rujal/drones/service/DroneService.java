package com.rujal.drones.service;

import com.rujal.drones.dto.DroneDTO;
import java.util.List;

public interface DroneService {

  /**
   * create a new record of Drone in the database
   */
  DroneDTO addDrone(DroneDTO droneDTO);

  /**
   * search and update specific records in the table
   */
  DroneDTO updateDrone(DroneDTO droneDTO);

  /**
   * search and retrieve specific records in the table
   */
  DroneDTO findDroneById(Long id);

  /**
   * delete specific records in the table
   */
  void deleteDrone(Long id);

  /**
   * Load Drone with Medication
   * @param droneId a unique primary Key Identifier for Drone
   * @param medicationIds is list of Medication primary key Identifier that are to be loaded in Drone
   * @return {@link DroneDTO} in which Medication is added to
   */
  DroneDTO addMedicationOnDrone(Long droneId, List<Long> medicationIds);
}
