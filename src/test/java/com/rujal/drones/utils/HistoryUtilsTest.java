package com.rujal.drones.utils;

import static com.rujal.drones.domain.EventType.ADD_DRONE;
import static com.rujal.drones.utils.Constants.Database.DRONE;
import static com.rujal.drones.utils.Model.LIGHT;
import static com.rujal.drones.utils.State.IDLE;
import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rujal.drones.domain.History;
import com.rujal.drones.dto.DroneDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HistoryUtilsTest extends AppDataTest{

  @Test
  void testCreateHistoryShouldReturnHistory() {
    // GIVEN
    DroneDTO droneDTO = createDroneDTO(ID);
    droneDTO.setBatteryCapacity(UPDATED_BATTERY);
    DroneDTO oldDroneDTO = createDroneDTO(ID);
    // WHEN
    History history = HistoryUtils.createHistory(DRONE, ID, oldDroneDTO, droneDTO, ADD_DRONE);
    // THEN
    assertNotNull(history);
    DroneDTO newDroneHistory = (DroneDTO) history.getNewValue();
    assertEquals(ID, newDroneHistory.getId());
    assertEquals(SERIAL_NUMBER, newDroneHistory.getSerialNumber());
    assertEquals(UPDATED_BATTERY, newDroneHistory.getBatteryCapacity());
    assertEquals(IDLE, newDroneHistory.getState());
    assertEquals(LIGHT, newDroneHistory.getModel());
    assertEquals(WEIGHT_LIMIT, newDroneHistory.getWeightLimit());
    assertFalse(isNull(history.getEventDetails().get(0).getOldValue()));
    assertFalse(isNull(history.getEventDetails().get(0).getNewValue()));
  }

}
