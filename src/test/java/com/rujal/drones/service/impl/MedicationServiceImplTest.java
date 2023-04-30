package com.rujal.drones.service.impl;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.rujal.drones.domain.Medication;
import com.rujal.drones.dto.MedicationDTO;
import com.rujal.drones.exceptions.ResourceNotFoundException;
import com.rujal.drones.repository.MedicationRepository;
import com.rujal.drones.utils.AppDataTest;
import java.util.List;
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
    Medication medication = mockMedication(ID);
    given(medicationRepository.save(any(Medication.class))).willReturn(medication);
    // When
    MedicationDTO medicationDTOResult = medicationService.addMedication(medicationDTO);
    // Then
    assertEquals(ID, medicationDTOResult.getId());
    verify(medicationRepository).save(any(Medication.class));
  }

  @Test
  void testUpdateMedicationWithMedicationCodeChangeOnExistingDataShouldUpdateMedication() {
    // Given
    MedicationDTO medicationDTO = mockMedicationDTO(ID);
    given(medicationDTO.getCode()).willReturn(MEDICATION_CODE_2);
    Medication medication = mockMedication(ID);
    ArgumentCaptor<String> medicationCodeCaptor = ArgumentCaptor.forClass(String.class);
    doNothing().when(medication).setCode(medicationCodeCaptor.capture());
    given(medicationRepository.findById(ID)).willReturn(Optional.of(medication));
    given(medicationRepository.save(any(Medication.class))).willReturn(medication);
    // When
    MedicationDTO medicationDTOResult = medicationService.updateMedication(medicationDTO);
    // Then
    assertEquals(ID, medicationDTOResult.getId());
    assertEquals(MEDICATION_CODE_2, medicationCodeCaptor.getValue());
  }

  @Test
  void testUpdateMedicationWithMedicationCodeChangeOnNoneExistingDataShouldThrowException() {
    // Given
    MedicationDTO medicationDTO = mockMedicationDTO(ID);
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
    Medication medication = mockMedication(ID);
    given(medicationRepository.findById(ID)).willReturn(Optional.of(medication));
    // When
    MedicationDTO resultMedicationDTO = medicationService.findMedicationById(ID);
    // Then
    assertNotNull(resultMedicationDTO);
    assertEquals(ID, resultMedicationDTO.getId());
    assertEquals(MEDICATION_CODE, resultMedicationDTO.getCode());
  }

  @Test
  void testFindMedicationByIdWithNoneExistingIdShouldThrowResourceNotFoundException() {
    // Given
    given(medicationRepository.findById(ID)).willReturn(empty());
    // When
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> medicationService.findMedicationById(ID));
    // Then
    assertEquals("Not Found : Medication with ID 1.",exception.getLocalizedMessage());
  }

  @Test
  void testDeleteMedicationWithExistingIdShouldDeleteMedication() {
    // Given
    Medication medication = mockMedication(ID);
    given(medicationRepository.findById(ID)).willReturn(Optional.of(medication));
    // When
    medicationService.deleteMedication(ID);
    // Then
    ArgumentCaptor<Medication> medicationArgumentCaptor = ArgumentCaptor.forClass(Medication.class);
    verify(medicationRepository).delete(medicationArgumentCaptor.capture());
    Medication medicationArgs = medicationArgumentCaptor.getValue();
    assertEquals(ID, medicationArgs.getId());
    assertMedication(medicationArgs);
  }

  @Test
  void testDeleteMedicationWithNoneExistingIdShouldThrowResourceNotFoundException() {
    // Given
    given(medicationRepository.findById(ID)).willReturn(empty());
    // When
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> medicationService.deleteMedication(ID));
    // Then
    assertEquals("Not Found : Medication with ID 1.",exception.getLocalizedMessage());
    verify(medicationRepository, never()).delete(any(Medication.class));
  }

  @Test
  void testFindMedicationByIdsShouldReturnMedications() {
    // Given
    List<Long> medicationIds = asList(ID, ID_2);
    List<Medication> medications = asList(mockMedication(ID), mockMedication(ID_2));
    given(medicationRepository.findAllById(medicationIds)).willReturn(medications);
    // When
    List<Medication> medicationList = medicationService.findMedicationByIds(medicationIds);
    // Then
    assertEquals(medicationIds.size(), medicationList.size());
    assertEquals(ID, medicationList.get(0).getId());
    assertMedication(medicationList.get(0));
    assertEquals(ID_2, medicationList.get(1).getId());
    assertMedication(medicationList.get(1));
  }

  @Test
  void testFindMedicationByIdsWithNoDataShouldReturnEmptyMedications() {
    // Given
    List<Long> medicationIds = asList(ID, ID_2);
    given(medicationRepository.findAllById(medicationIds)).willReturn(emptyList());
    // When
    List<Medication> medicationList = medicationService.findMedicationByIds(medicationIds);
    // Then
    assertTrue(isEmpty(medicationList));
  }
}
