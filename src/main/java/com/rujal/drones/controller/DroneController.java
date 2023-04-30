package com.rujal.drones.controller;

import static com.rujal.drones.utils.Constants.Path.ADD_MEDICATIONS;
import static com.rujal.drones.utils.Constants.Path.AVAILABLE_DRONES;
import static com.rujal.drones.utils.Constants.Path.DRONE_BASE_URL;
import static com.rujal.drones.utils.Constants.Path.ID;
import static com.rujal.drones.utils.Constants.Path.PATH_PARAM_ID;
import static com.rujal.drones.utils.MessagePropertyConstants.DELETED_DRONE;
import static com.rujal.drones.utils.MessageUtils.getMessage;
import static com.rujal.drones.utils.Response.success;
import static org.springframework.http.HttpStatus.CREATED;

import com.rujal.drones.dto.DroneDTO;
import com.rujal.drones.service.DroneService;
import com.rujal.drones.utils.Constants.Path;
import com.rujal.drones.utils.Response;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST APIs for Drone
 */
@RestController
@RequestMapping(DRONE_BASE_URL)
public class DroneController {

  private static final Logger LOG = LoggerFactory.getLogger(DroneController.class);

  private final DroneService droneService;

  public DroneController(DroneService droneService) {
    this.droneService = droneService;
  }

  /**
   * API to create entry of Drone on Table
   *
   * @param {@link DroneDTO
   * @return {@link DroneDTO}
   */
  @PostMapping
  public ResponseEntity<Response> createDrone(@Valid @RequestBody DroneDTO droneDTO) {
    LOG.info("Request for adding new Drone");
    return new ResponseEntity<>(success(droneService.addDrone(droneDTO)), CREATED);
  }

  /**
   * API to update existing entry on Table
   *
   * @param {@link DroneDTO} should contain unique identifier for successful updates
   * @return {@link DroneDTO}
   */
  @PutMapping
  public ResponseEntity<Response> updateDrone(@Valid @RequestBody DroneDTO droneDTO) {
    LOG.info("Request for Updating Drone : {}", droneDTO.getId());
    return ResponseEntity.ok(success(droneService.updateDrone(droneDTO)));
  }

  /**
   * Fetches the Drone by ID
   *
   * @param id is a unique identifier or primary key
   * @return {@link DroneDTO}
   */
  @GetMapping(Path.PATH_PARAM_ID)
  public ResponseEntity<Response> getDrone(@PathVariable Long id) {
    LOG.info("Request for Fetching Drone : {}", id);
    return ResponseEntity.ok(success(droneService.findDroneById(id)));
  }

  /**
   * Deletes the Drone by ID
   *
   * @param id is a unique identifier or primary key
   */
  @DeleteMapping(PATH_PARAM_ID)
  public ResponseEntity<Response> deleteDrone(@PathVariable Long id) {
    LOG.info("Request for Deleting Drone : {}", id);
    droneService.deleteDrone(id);
    return ResponseEntity.ok(success(getMessage(DELETED_DRONE.getValue(), id)));
  }

  /**
   * Load Medication on Drone
   */
  @PostMapping(PATH_PARAM_ID + ADD_MEDICATIONS)
  public ResponseEntity<Response> loadMedicationOnDrone(@PathVariable(ID) Long droneId,
      @RequestBody List<Long> medicationIds) {
    LOG.info("Request for Loading Medication on Drone : {}", droneId);
    return ResponseEntity.ok(success(droneService.addMedicationOnDrone(droneId, medicationIds)));
  }

  /**
   * Fetch Available Drone
   * @return
   */
  @GetMapping(AVAILABLE_DRONES)
  public ResponseEntity<Response> checkAvailableDrones() {
    LOG.info("Request for checking Available Drones");
    return ResponseEntity.ok(success(droneService.checkAvailableDrones()));
  }
}
