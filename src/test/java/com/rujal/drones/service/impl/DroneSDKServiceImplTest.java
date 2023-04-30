package com.rujal.drones.service.impl;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.rujal.drones.dto.DroneDTO;
import com.rujal.drones.utils.AppDataTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DroneSDKServiceImplTest extends AppDataTest {

  @InjectMocks
  private DroneSDKServiceImpl droneSDKService;

  @Test
  void testGetCurrentDroneDetailsShouldReturnUpdatedDroneDetails() {
    // GIVEN
    DroneDTO droneDTO1 = mockDroneDTO(ID, SERIAL_NUMBER);
    droneDTO1.setBatteryCapacity(ONE);
    DroneDTO droneDTO2 = mockDroneDTO(ID_2, SERIAL_NUMBER_2);
    droneDTO1.setBatteryCapacity(TEN);
    List<DroneDTO> droneDTOS = asList(droneDTO1, droneDTO2);
    // WHEN
    List<DroneDTO> resultDroneDTOS = droneSDKService.getCurrentDroneDetails(droneDTOS);
    // THEN
    assertEquals(ID, resultDroneDTOS.get(0).getId());
    assertEquals(SERIAL_NUMBER, resultDroneDTOS.get(0).getSerialNumber());
    assertNotEquals(ONE, resultDroneDTOS.get(0).getBatteryCapacity());
    assertEquals(ID_2, resultDroneDTOS.get(1).getId());
    assertEquals(SERIAL_NUMBER_2, resultDroneDTOS.get(1).getSerialNumber());
    assertNotEquals(TEN, resultDroneDTOS.get(1).getBatteryCapacity());
  }
}
