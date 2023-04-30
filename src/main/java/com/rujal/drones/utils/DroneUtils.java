package com.rujal.drones.utils;

import static com.rujal.drones.utils.MessagePropertyConstants.DRONE_NOT_FOUND;
import static com.rujal.drones.utils.MessageUtils.getMessage;
import static com.rujal.drones.utils.State.IDLE;
import static com.rujal.drones.utils.State.LOADING;

import com.rujal.drones.domain.Drone;
import com.rujal.drones.exceptions.ResourceNotFoundException;
import java.math.BigDecimal;
import java.util.function.Supplier;

public class DroneUtils {

  private DroneUtils() {}

  /**
   * Throw Drone Not Found Exception
   */
  public static Supplier<ResourceNotFoundException> exceptionDroneNotFound(Long id) {
    return () -> new ResourceNotFoundException(getMessage(DRONE_NOT_FOUND.getValue(), id));
  }

  /**
   * Check if Drone has Sufficient Battery
   */
  public static boolean hasSufficientBattery(Drone drone) {
    return drone.getBatteryCapacity().compareTo(new BigDecimal(25)) > 0;
  }

  /**
   * Check if Drone is Loadable
   */
  public static boolean isDroneLoadable(Drone drone) {
    return hasSufficientBattery(drone) && IDLE.equals(drone.getState());
  }

  /**
   * Check if Drone is Not Loadable
   */
  public static boolean isDroneNotLoadable(Drone drone) {
    return !isDroneLoadable(drone);
  }

  /**
   * Set Drone state as LOADING
   */
  public static Drone updateStateLoading(Drone drone) {
    drone.setState(LOADING);
    return drone;
  }
}
