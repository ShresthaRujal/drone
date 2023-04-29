package com.rujal.drones.utils;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.rujal.drones.domain.Medication;
import com.rujal.drones.dto.MedicationDTO;

public abstract class AppDataTest {

  public static final Long MEDICATION_ID = 1L;
  public static final Long MEDICATION_ID_2 = 2L;
  public static final String MEDICATION_CODE = "CODE_MED_1";
  public static final String MEDICATION_CODE_2 = "CODE_MED_2";
  public static final String MEDICATION_IMAGE = "path://";
  public static final String MEDICATION_NAME = "MED_NAME";
  public static final int MEDICATION_WEIGHT = 100;
  public static final String SERIAL_NUMBER = "SERIAL_1";

  public MedicationDTO mockMedicationDTO(Long id) {
    MedicationDTO medicationDTO = mock(MedicationDTO.class);
    given(medicationDTO.getId()).willReturn(id);
    return medicationDTO;
  }

  public static MedicationDTO createMedicationDTO(Long id) {
    MedicationDTO medicationDTO = new MedicationDTO();
    medicationDTO.setId(id);
    medicationDTO.setCode(MEDICATION_CODE);
    medicationDTO.setName(MEDICATION_NAME);
    medicationDTO.setWeight(MEDICATION_WEIGHT);
    return medicationDTO;
  }

  public Medication mockMedication(Long id) {
    Medication medication = mock(Medication.class);
    given(medication.getId()).willReturn(id);
    given(medication.getCode()).willReturn(MEDICATION_CODE);
    given(medication.getImage()).willReturn(MEDICATION_IMAGE);
    given(medication.getName()).willReturn(MEDICATION_NAME);
    given(medication.getWeight()).willReturn(MEDICATION_WEIGHT);
    return medication;
  }
}
