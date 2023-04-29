package com.rujal.drones.controller;

import static com.rujal.drones.utils.Constants.Path.MEDICATION_BASE_URL;
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

import com.rujal.drones.dto.MedicationDTO;
import com.rujal.drones.utils.BeanConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MedicationController.class)
class MedicationControllerTest extends BeanConfigTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testCreateMedication() throws Exception {
    given(medicationService.addMedication(any(MedicationDTO.class)))
        .willReturn(createMedicationDTO(ID));
    mockMvc.perform(post(BASE_PATH + MEDICATION_BASE_URL)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createMedicationDTO(ID)))
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.data.id").exists())
        .andExpect(jsonPath("$.data.name", equalTo(MEDICATION_NAME)))
        .andExpect(jsonPath("$.data.code", equalTo(MEDICATION_CODE)))
        .andExpect(jsonPath("$.data.weight", equalTo(MEDICATION_WEIGHT)));
  }

  @Test
  void testUpdateMedication() throws Exception {
    MedicationDTO updateMedicationDT0 = createMedicationDTO(ID);
    given(medicationService.updateMedication(any(MedicationDTO.class)))
        .willReturn(updateMedicationDT0);
    updateMedicationDT0.setCode(MEDICATION_CODE_2);
    mockMvc.perform(put(BASE_PATH + MEDICATION_BASE_URL)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateMedicationDT0))
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id").exists())
        .andExpect(jsonPath("$.data.name", equalTo(MEDICATION_NAME)))
        .andExpect(jsonPath("$.data.code", equalTo(MEDICATION_CODE_2)))
        .andExpect(jsonPath("$.data.weight", equalTo(MEDICATION_WEIGHT)));
  }

  @Test
  void testFindMedicationById() throws Exception {
    MedicationDTO medicationDTO = createMedicationDTO(ID);
    given(medicationService.findMedicationById(ID))
        .willReturn(medicationDTO);
    mockMvc.perform(get(BASE_PATH + MEDICATION_BASE_URL + "/" + ID)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id").exists())
        .andExpect(jsonPath("$.data.name", equalTo(MEDICATION_NAME)))
        .andExpect(jsonPath("$.data.code", equalTo(MEDICATION_CODE)))
        .andExpect(jsonPath("$.data.weight", equalTo(MEDICATION_WEIGHT)));
  }

  @Test
  void testDeleteMedicationById() throws Exception {
    mockMvc.perform(delete(BASE_PATH + MEDICATION_BASE_URL + "/" + ID)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    verify(medicationService).deleteMedication(any());
  }
}
