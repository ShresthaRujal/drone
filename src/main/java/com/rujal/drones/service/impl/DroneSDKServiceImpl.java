package com.rujal.drones.service.impl;

import com.rujal.drones.dto.DroneDTO;
import com.rujal.drones.service.DroneSDKService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DroneSDKServiceImpl implements DroneSDKService {

  /**
   * Communication with Drone
   */
  @Override
  public List<DroneDTO> getCurrentDroneDetails(List<DroneDTO> drones) {
    return drones.stream().map(this::getDroneLatestBattery).toList();
  }

  /**
   * function that provides Dummy Data against Drone
   */
  private DroneDTO getDroneLatestBattery(DroneDTO drone) {
    BigDecimal min = BigDecimal.ZERO;
    BigDecimal max = new BigDecimal(100);
    BigDecimal newBatteryStatus = min.add(BigDecimal.valueOf(Math.random())
        .multiply(max.subtract(min)));
    drone.setBatteryCapacity(newBatteryStatus.setScale(2, RoundingMode.HALF_UP));
    return drone;
  }
}
