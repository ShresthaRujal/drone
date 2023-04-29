package com.rujal.drones.controller;

import static com.rujal.drones.utils.Constants.Path.DRONE_BASE_URL;
import static com.rujal.drones.utils.Model.LIGHT;
import static com.rujal.drones.utils.State.IDLE;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rujal.drones.dto.DroneDTO;
import com.rujal.drones.utils.BeanConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DroneController.class)
class DroneControllerTest extends BeanConfigTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void testAddDrone() throws Exception {
    given(droneService.addDrone(any(DroneDTO.class))).willReturn(createDroneDTO(ID));
    mockMvc.perform(post(BASE_PATH + DRONE_BASE_URL)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createMedicationDTO(ID)))
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.data.id").exists())
        .andExpect(jsonPath("$.data.state", equalTo(IDLE.name())))
        .andExpect(jsonPath("$.data.model", equalTo(LIGHT.name())))
        .andExpect(jsonPath("$.data.batteryCapacity", equalTo(BATTERY.intValue())))
        .andExpect(jsonPath("$.data.weightLimit", equalTo(WEIGHT_LIMIT)))
        .andExpect(jsonPath("$.data.serialNumber", equalTo(SERIAL_NUMBER)));
  }

  @Test
  void testUpdateDrone() throws Exception {
    DroneDTO updatedDrone = createDroneDTO(ID);
    given(droneService.updateDrone(any(DroneDTO.class)))
        .willReturn(updatedDrone);
    updatedDrone.setSerialNumber(SERIAL_NUMBER_2);
    mockMvc.perform(put(BASE_PATH + DRONE_BASE_URL)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedDrone))
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id").exists())
        .andExpect(jsonPath("$.data.state", equalTo(IDLE.name())))
        .andExpect(jsonPath("$.data.model", equalTo(LIGHT.name())))
        .andExpect(jsonPath("$.data.batteryCapacity", equalTo(BATTERY.intValue())))
        .andExpect(jsonPath("$.data.weightLimit", equalTo(WEIGHT_LIMIT)))
        .andExpect(jsonPath("$.data.serialNumber", equalTo(SERIAL_NUMBER_2)));
  }

  @Test
  void testFindDroneById() throws Exception {
    DroneDTO droneDTO = createDroneDTO(ID);
    given(droneService.findDroneById(ID)).willReturn(droneDTO);
    mockMvc.perform(get(BASE_PATH + DRONE_BASE_URL + "/" + ID)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id").exists())
        .andExpect(jsonPath("$.data.state", equalTo(IDLE.name())))
        .andExpect(jsonPath("$.data.model", equalTo(LIGHT.name())))
        .andExpect(jsonPath("$.data.batteryCapacity", equalTo(BATTERY.intValue())))
        .andExpect(jsonPath("$.data.weightLimit", equalTo(WEIGHT_LIMIT)))
        .andExpect(jsonPath("$.data.serialNumber", equalTo(SERIAL_NUMBER)));
  }

  @Test
  void testDeleteMedicationById() throws Exception {
    mockMvc.perform(delete(BASE_PATH + DRONE_BASE_URL + "/" + ID)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    verify(droneService).deleteDrone(any());
  }
}
