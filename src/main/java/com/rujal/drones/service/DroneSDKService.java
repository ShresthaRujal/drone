package com.rujal.drones.service;

import com.rujal.drones.dto.DroneDTO;
import java.util.List;

/**
 * Drone Communication Assumption
 */
public interface DroneSDKService {

  List<DroneDTO> getCurrentDroneDetails(List<DroneDTO> drones);
}
