package com.rujal.drones.service;

import com.rujal.drones.domain.Medication;
import com.rujal.drones.dto.MedicationDTO;
import java.util.List;

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
  MedicationDTO findMedicationById(Long id);

  /**
   * delete specific records in the table
   */
  void deleteMedication(Long id);

  List<Medication> findMedicationByIds(List<Long> medicationIds);
}
