package com.rujal.drones.utils;

import static com.rujal.drones.utils.MessagePropertyConstants.MEDICATION_NOT_FOUND;
import static com.rujal.drones.utils.MessageUtils.getMessage;

import com.rujal.drones.exceptions.ResourceNotFoundException;
import java.util.function.Supplier;

public class MedicationUtils {

  private MedicationUtils() {
  }

  /**
   * Throw error Medication Not Found
   */
  public static Supplier<ResourceNotFoundException> errorMedicationNotFound(Long id) {
    return () -> new ResourceNotFoundException(getMessage(MEDICATION_NOT_FOUND.getValue(), id));
  }

}
