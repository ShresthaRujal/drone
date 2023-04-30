package com.rujal.drones.service.impl;

import static com.rujal.drones.utils.Model.LIGHT;
import static com.rujal.drones.utils.State.IDLE;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.rujal.drones.domain.Drone;
import com.rujal.drones.dto.DroneDTO;
import com.rujal.drones.exceptions.ResourceNotFoundException;
import com.rujal.drones.repository.DroneRepository;
import com.rujal.drones.utils.AppDataTest;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest extends AppDataTest {

  @InjectMocks
  private DroneServiceImpl droneService;
  @Mock
  private DroneRepository droneRepository;

  @Test
  void testAddDroneShouldAddDrone() {
    // Given
    DroneDTO droneDTO = mockDroneDTO(null);
    Drone drone = mockDrone(ID);
    given(droneRepository.save(any(Drone.class))).willReturn(drone);
    // When
    DroneDTO droneDTOResult = droneService.addDrone(droneDTO);
    // Then
    assertEquals(ID, droneDTOResult.getId());
    ArgumentCaptor<Drone> droneArgumentCaptor = ArgumentCaptor.forClass(Drone.class);
    verify(droneRepository).save(droneArgumentCaptor.capture());
  }

  @Test
  void testUpdateDroneWithDroneSerialNumberChangeOnExistingDataShouldUpdateDrone() {
    // Given
    DroneDTO droneDTO = mockDroneDTO(ID);
    given(droneDTO.getSerialNumber()).willReturn(SERIAL_NUMBER_2);
    Drone drone = mockDrone(ID);
    ArgumentCaptor<String> droneSerialNumberCaptor = ArgumentCaptor.forClass(String.class);
    doNothing().when(drone).setSerialNumber(droneSerialNumberCaptor.capture());
    given(droneRepository.findById(ID)).willReturn(Optional.of(drone));
    given(droneRepository.save(any(Drone.class))).willReturn(drone);
    // When
    DroneDTO droneDTOResult = droneService.updateDrone(droneDTO);
    // Then
    assertEquals(ID, droneDTOResult.getId());
    assertEquals(SERIAL_NUMBER_2, droneSerialNumberCaptor.getValue());
  }

  @Test
  void testUpdateDroneWithDroneSerialNumberChangeOnNoneExistingDataShouldThrowException() {
    // Given
    DroneDTO droneDTO = mockDroneDTO(ID);
    given(droneRepository.findById(droneDTO.getId())).willReturn(empty());
    // When
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> droneService.updateDrone(droneDTO));
    // Then
    assertEquals("Not Found : Drone with ID 1.",exception.getLocalizedMessage());
  }

  @Test
  void testFindDroneByIdWithExistingIdShouldReturnDrone() {
    // Given
    Drone drone = mockDrone(ID);
    given(droneRepository.findById(ID)).willReturn(Optional.of(drone));
    // When
    DroneDTO resultDroneDTO = droneService.findDroneById(ID);
    // Then
    assertNotNull(resultDroneDTO);
    assertEquals(ID, resultDroneDTO.getId());
    assertEquals(SERIAL_NUMBER, resultDroneDTO.getSerialNumber());
  }

  @Test
  void testFindDroneByIdWithNoneExistingIdShouldThrowResourceNotFoundException() {
    // Given
    given(droneRepository.findById(ID)).willReturn(empty());
    // When
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> droneService.findDroneById(ID));
    // Then
    assertEquals("Not Found : Drone with ID 1.",exception.getLocalizedMessage());
  }

  @Test
  void testDeleteDroneWithExistingIdShouldDeleteDrone() {
    // Given
    Drone drone = mockDrone(ID);
    given(droneRepository.findById(ID)).willReturn(Optional.of(drone));
    // When
    droneService.deleteDrone(ID);
    // Then
    ArgumentCaptor<Drone> droneArgumentCaptor = ArgumentCaptor.forClass(Drone.class);
    verify(droneRepository).delete(droneArgumentCaptor.capture());
    Drone droneValue = droneArgumentCaptor.getValue();
    assertEquals(ID, droneValue.getId());
    assertEquals(SERIAL_NUMBER, droneValue.getSerialNumber());
    assertEquals(BATTERY, droneValue.getBatteryCapacity());
    assertEquals(LIGHT, droneValue.getModel());
    assertEquals(WEIGHT_LIMIT, droneValue.getWeightLimit());
    assertEquals(IDLE, droneValue.getState());
  }

  @Test
  void testDeleteDroneWithNoneExistingIdShouldThrowResourceNotFoundException() {
    // Given
    given(droneRepository.findById(ID)).willReturn(empty());
    // When
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> droneService.deleteDrone(ID));
    // Then
    assertEquals("Not Found : Drone with ID 1.",exception.getLocalizedMessage());
    verify(droneRepository, never()).delete(any(Drone.class));
  }
}
