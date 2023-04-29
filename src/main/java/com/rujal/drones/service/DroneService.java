package com.rujal.drones.service;

import com.rujal.drones.dto.DroneDTO;

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
}
