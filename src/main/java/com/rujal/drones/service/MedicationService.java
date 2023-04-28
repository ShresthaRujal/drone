package com.rujal.drones.service;

import com.rujal.drones.dto.MedicationDTO;

public interface MedicationService {

  /**
   * create a new record of Medication in the database
   */
  MedicationDTO addMedication(MedicationDTO medicationDTO);

  /**
   * search and update specific records in the table
   */
  MedicationDTO updateMedication(MedicationDTO medicationDTO);

  /**
   * search and retrieve specific records in the table
   */
  MedicationDTO findMedicationById(Long ID);

  /**
   * delete specific records in the table
   */
  void deleteMedication(Long ID);
}
