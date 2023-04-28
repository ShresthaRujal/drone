package com.rujal.drones.service.impl;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.rujal.drones.domain.Medication;
import com.rujal.drones.dto.MedicationDTO;
import com.rujal.drones.exceptions.ResourceNotFoundException;
import com.rujal.drones.repository.MedicationRepository;
import com.rujal.drones.utils.AppDataTest;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MedicationServiceImplTest extends AppDataTest {

  @InjectMocks
  private MedicationServiceImpl medicationService;
  @Mock
  private MedicationRepository medicationRepository;

  @Test
  void testAddMedicationShouldAddMedication() {
    // Given
    MedicationDTO medicationDTO = mockMedicationDTO(null);
    Medication medication = mockMedication(MEDICATION_ID);
    given(medicationRepository.save(any(Medication.class))).willReturn(medication);
    // When
    MedicationDTO medicationDTOResult = medicationService.addMedication(medicationDTO);
    // Then
    assertEquals(MEDICATION_ID, medicationDTOResult.getId());
    verify(medicationRepository).save(any(Medication.class));
  }

  @Test
  void testUpdateMedicationWithMedicationCodeChangeOnExistingDataShouldUpdateMedication() {
    // Given
    MedicationDTO medicationDTO = mockMedicationDTO(MEDICATION_ID);
    given(medicationDTO.getCode()).willReturn(MEDICATION_CODE_2);
    Medication medication = mockMedication(MEDICATION_ID);
    ArgumentCaptor<String> medicationCodeCaptor = ArgumentCaptor.forClass(String.class);
    doNothing().when(medication).setCode(medicationCodeCaptor.capture());
    given(medicationRepository.findById(MEDICATION_ID)).willReturn(Optional.of(medication));
    given(medicationRepository.save(any(Medication.class))).willReturn(medication);
    // When
    MedicationDTO medicationDTOResult = medicationService.updateMedication(medicationDTO);
    // Then
    assertEquals(MEDICATION_ID, medicationDTOResult.getId());
    assertEquals(MEDICATION_CODE_2, medicationCodeCaptor.getValue());
  }

  @Test
  void testUpdateMedicationWithMedicationCodeChangeOnNoneExistingDataShouldThrowException() {
    // Given
    MedicationDTO medicationDTO = mockMedicationDTO(MEDICATION_ID);
    given(medicationRepository.findById(medicationDTO.getId())).willReturn(empty());
    // When
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> medicationService.updateMedication(medicationDTO));
    // Then
    assertEquals("Not Found : Medication with ID 1.",exception.getLocalizedMessage());
  }

  @Test
  void testFindMedicationByIdWithExistingIdShouldReturnMedication() {
    // Given
    Medication medication = mockMedication(MEDICATION_ID);
    given(medicationRepository.findById(MEDICATION_ID)).willReturn(Optional.of(medication));
    // When
    MedicationDTO resultMedicationDTO = medicationService.findMedicationById(MEDICATION_ID);
    // Then
    assertNotNull(resultMedicationDTO);
    assertEquals(MEDICATION_ID, resultMedicationDTO.getId());
    assertEquals(MEDICATION_CODE, resultMedicationDTO.getCode());
  }

  @Test
  void testFindMedicationByIdWithNoneExistingIdShouldThrowResourceNotFoundException() {
    // Given
    given(medicationRepository.findById(MEDICATION_ID)).willReturn(empty());
    // When
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> medicationService.findMedicationById(MEDICATION_ID));
    // Then
    assertEquals("Not Found : Medication with ID 1.",exception.getLocalizedMessage());
  }

  @Test
  void testDeleteMedicationWithExistingIdShouldDeleteMedication() {
    // Given
    Medication medication = mockMedication(MEDICATION_ID);
    given(medicationRepository.findById(MEDICATION_ID)).willReturn(Optional.of(medication));
    // When
    medicationService.deleteMedication(MEDICATION_ID);
    // Then
    ArgumentCaptor<Medication> medicationArgumentCaptor = ArgumentCaptor.forClass(Medication.class);
    verify(medicationRepository).delete(medicationArgumentCaptor.capture());
    Medication medicationArgs = medicationArgumentCaptor.getValue();
    assertEquals(MEDICATION_ID, medicationArgs.getId());
    assertEquals(MEDICATION_CODE, medicationArgs.getCode());
    assertEquals(MEDICATION_IMAGE, medicationArgs.getImage());
    assertEquals(MEDICATION_NAME, medicationArgs.getName());
    assertEquals(MEDICATION_WEIGHT, medicationArgs.getWeight());
  }

  @Test
  void testDeleteMedicationWithNoneExistingIdShouldThrowResourceNotFoundException() {
    // Given
    given(medicationRepository.findById(MEDICATION_ID)).willReturn(empty());
    // When
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> medicationService.deleteMedication(MEDICATION_ID));
    // Then
    assertEquals("Not Found : Medication with ID 1.",exception.getLocalizedMessage());
    verify(medicationRepository, never()).delete(any(Medication.class));
  }
}
