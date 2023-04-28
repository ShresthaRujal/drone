package com.rujal.drones.utils;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.rujal.drones.domain.Medication;
import com.rujal.drones.dto.MedicationDTO;

public class AppDataTest {

  protected static final Long MEDICATION_ID = 1L;
  protected static final Long MEDICATION_ID_2 = 2L;
  protected static final String MEDICATION_CODE = "CODE_MED_1";
  protected static final String MEDICATION_CODE_2 = "CODE_MED_2";
  protected static final String MEDICATION_IMAGE = "path://";
  protected static final String MEDICATION_NAME = "MED_NAME";
  protected static final int MEDICATION_WEIGHT = 100;
  protected static final String SERIAL_NUMBER = "SERIAL_1";

  public MedicationDTO mockMedicationDTO(Long ID) {
    MedicationDTO medicationDTO = mock(MedicationDTO.class);
    given(medicationDTO.getId()).willReturn(ID);
    return medicationDTO;
  }

  public Medication mockMedication(Long ID) {
    Medication medication = mock(Medication.class);
    given(medication.getId()).willReturn(ID);
    given(medication.getCode()).willReturn(MEDICATION_CODE);
    given(medication.getImage()).willReturn(MEDICATION_IMAGE);
    given(medication.getName()).willReturn(MEDICATION_NAME);
    given(medication.getWeight()).willReturn(MEDICATION_WEIGHT);
    return medication;
  }
}
