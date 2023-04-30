package com.rujal.drones.scheduler;

import com.rujal.drones.service.DroneService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulerConfig {

  private final DroneService droneService;
  /**
   * Schedule drone health check. 60000 is equals to 1 minute
   */
  private static final int SCHEDULE_DRONE_HEALTH_CHECK = 60000;

  SchedulerConfig(DroneService droneService) {
    this.droneService = droneService;
  }

  /**
   * Periodic task to check and update Drone Status
   */
  @Scheduled(fixedDelay = SCHEDULE_DRONE_HEALTH_CHECK)
  public void checkAndAuditDroneBattery() {
    droneService.checkAndAuditDroneBatteryLevel();
  }
}
