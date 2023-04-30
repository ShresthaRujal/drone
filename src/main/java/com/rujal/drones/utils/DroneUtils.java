package com.rujal.drones.utils;

import static com.rujal.drones.utils.MessagePropertyConstants.DRONE_NOT_AVAILABLE_NOW;
import static com.rujal.drones.utils.MessagePropertyConstants.DRONE_NOT_FOUND;
import static com.rujal.drones.utils.MessageUtils.getMessage;
import static com.rujal.drones.utils.State.DELIVERED;
import static com.rujal.drones.utils.State.DELIVERING;
import static com.rujal.drones.utils.State.IDLE;
import static com.rujal.drones.utils.State.LOADED;
import static com.rujal.drones.utils.State.LOADING;
import static com.rujal.drones.utils.State.RETURNING;

import com.rujal.drones.domain.Drone;
import com.rujal.drones.exceptions.ResourceNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

public class DroneUtils {

  private DroneUtils() {}

  private static final List<State> DRONE_IN_WORK = List.of(RETURNING, DELIVERED, DELIVERING, LOADED);

  /**
   * Throw Drone Not Found Exception
   */
  public static Supplier<ResourceNotFoundException> exceptionDroneNotFound(Long id) {
    return () -> new ResourceNotFoundException(getMessage(DRONE_NOT_FOUND.getValue(), id));
  }

  /**
   * Throw Drone Not Found Exception
   */
  public static Supplier<ResourceNotFoundException> exceptionDroneNotAvailable(Long id) {
    return () -> new ResourceNotFoundException(getMessage(DRONE_NOT_AVAILABLE_NOW.getValue(), id));
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
   * Set Drone state as LOADING
   */
  public static Drone updateState(Drone drone) {
    if (!DRONE_IN_WORK.contains(drone.getState()) && hasSufficientBattery(drone)) {
      drone.setState(IDLE);
    }
    if (!hasSufficientBattery(drone)) {
      drone.setState(LOADING);
    }
    return drone;
  }
}
