package com.rujal.drones.controller;

import static com.rujal.drones.utils.Constants.Path.ADD_MEDICATIONS;
import static com.rujal.drones.utils.Constants.Path.AVAILABLE_DRONES;
import static com.rujal.drones.utils.Constants.Path.DRONE_BASE_URL;
import static com.rujal.drones.utils.Constants.Path.PATH_PARAM_ID;
import static com.rujal.drones.utils.Model.LIGHT;
import static com.rujal.drones.utils.State.IDLE;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
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
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DroneController.class)
class DroneControllerTest extends BeanConfigTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void testAddDrone() throws Exception {
    given(droneService.addDrone(any(DroneDTO.class))).willReturn(createDroneDTO(ID));
    mockMvc.perform(post(BASE_PATH + DRONE_BASE_URL)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createDroneDTO(ID)))
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

  @Test
  void testLoadMedicationOnDrone() throws Exception {
    List<Long> medicationIds = List.of(ID);
    given(droneService.addMedicationOnDrone(ID, medicationIds)).willReturn(createDroneDTO(ID));
    mockMvc.perform(post(BASE_PATH + DRONE_BASE_URL+ "/1" + ADD_MEDICATIONS)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(medicationIds))
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
  void testCheckAvailableDrones() throws Exception {
    DroneDTO droneDTO = createDroneDTO(ID);
    given(droneService.checkAvailableDrones()).willReturn(List.of(droneDTO));
    mockMvc.perform(get(BASE_PATH + DRONE_BASE_URL + "/" + AVAILABLE_DRONES)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data[0].id").exists())
        .andExpect(jsonPath("$.data[0].state", equalTo(IDLE.name())))
        .andExpect(jsonPath("$.data[0].model", equalTo(LIGHT.name())))
        .andExpect(jsonPath("$.data[0].batteryCapacity", equalTo(BATTERY.intValue())))
        .andExpect(jsonPath("$.data[0].weightLimit", equalTo(WEIGHT_LIMIT)))
        .andExpect(jsonPath("$.data[0].serialNumber", equalTo(SERIAL_NUMBER)));
  }
}
