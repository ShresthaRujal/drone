package com.rujal.drones.controller;

import static com.rujal.drones.utils.Constants.Path.DELETE_BY_ID;
import static com.rujal.drones.utils.Constants.Path.FETCH_BY_ID;
import static com.rujal.drones.utils.Constants.Path.MEDICATION_BASE_URL;
import static com.rujal.drones.utils.MessagePropertyConstants.DELETED;
import static com.rujal.drones.utils.MessageUtils.getMessage;
import static com.rujal.drones.utils.Response.success;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.rujal.drones.dto.MedicationDTO;
import com.rujal.drones.service.MedicationService;
import com.rujal.drones.utils.Response;
import jakarta.validation.Valid;
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
 * REST APIs for Medication
 */
@RestController
@RequestMapping(MEDICATION_BASE_URL)
public class MedicationController {

  private static final Logger LOG = LoggerFactory.getLogger(MedicationController.class);

  private final MedicationService medicationService;

  public MedicationController(MedicationService medicationService) {
    this.medicationService = medicationService;
  }

  /**
   * API to create entry of Medication on Table
   *
   * @param {@link MedicationDTO}
   * @return {@link MedicationDTO}
   */
  @PostMapping
  public ResponseEntity<Response> createMedication(
      @Valid @RequestBody MedicationDTO medicationDTO
  ) {
    LOG.info("Received a request to add new Medication");
    return new ResponseEntity<>(success(medicationService.addMedication(medicationDTO)), CREATED);
  }

  /**
   * API to update existing entry on Table
   *
   * @param {@link MedicationDTO} should contain unique identifier for successful updates
   * @return {@link MedicationDTO}
   */
  @PutMapping
  public ResponseEntity<Response> updateMedication(@Valid @RequestBody MedicationDTO medicationDTO) {
    LOG.info("Update Medication : {}", medicationDTO.getId());
    return ResponseEntity.ok(success(medicationService.updateMedication(medicationDTO)));
  }

  /**
   * Fetches the Medication by ID
   * @param id is a unique identifier or primary key
   * @return {@link MedicationDTO}
   */
  @GetMapping(FETCH_BY_ID)
  public ResponseEntity<MedicationDTO> getMedication(@PathVariable Long id) {
    LOG.info("Fetching Medication : {}", id);
    return new ResponseEntity<>(medicationService.findMedicationById(id), OK);
  }

  /**
   * Deletes the Medication by ID
   * @param id is a unique identifier or primary key
   */
  @DeleteMapping(DELETE_BY_ID)
  public ResponseEntity<String> deleteMedication(@PathVariable Long id) {
    LOG.info("Deleting Medication : {}", id);
    medicationService.deleteMedication(id);
    return ResponseEntity.ok(getMessage(DELETED.getValue(), id));
  }
}
