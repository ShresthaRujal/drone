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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rujal.drones.converters.MedicationConverter;
import com.rujal.drones.dto.MedicationDTO;
import com.rujal.drones.utils.BeanConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MedicationController.class)
class MedicationControllerTest extends BeanConfigTest {

  private final ObjectMapper objectMapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;

  @Test
  void testCreateMedication() throws Exception {
    given(medicationService.addMedication(any(MedicationDTO.class)))
        .willReturn(createMedicationDTO(null));
    mockMvc.perform(post(BASE_PATH + MEDICATION_BASE_URL)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createMedicationDTO(MEDICATION_ID)))
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name", equalTo(MEDICATION_NAME)))
        .andExpect(jsonPath("$.code", equalTo(MEDICATION_CODE)))
        .andExpect(jsonPath("$.weight", equalTo(MEDICATION_WEIGHT)));
  }

  @Test
  void testUpdateMedication() throws Exception {
    MedicationDTO updateMedicationDT0 = createMedicationDTO(MEDICATION_ID);
    given(medicationService.updateMedication(any(MedicationDTO.class)))
        .willReturn(updateMedicationDT0);
    updateMedicationDT0.setCode(MEDICATION_CODE_2);
    mockMvc.perform(put(BASE_PATH + MEDICATION_BASE_URL)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateMedicationDT0))
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name", equalTo(MEDICATION_NAME)))
        .andExpect(jsonPath("$.code", equalTo(MEDICATION_CODE_2)))
        .andExpect(jsonPath("$.weight", equalTo(MEDICATION_WEIGHT)));
  }

  @Test
  void testFindMedicationById() throws Exception {
    MedicationDTO medicationDTO = createMedicationDTO(MEDICATION_ID);
    given(medicationService.findMedicationById(MEDICATION_ID))
        .willReturn(medicationDTO);
    mockMvc.perform(get(BASE_PATH + MEDICATION_BASE_URL + "/" + MEDICATION_ID)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name", equalTo(MEDICATION_NAME)))
        .andExpect(jsonPath("$.code", equalTo(MEDICATION_CODE)))
        .andExpect(jsonPath("$.weight", equalTo(MEDICATION_WEIGHT)));
  }

  @Test
  void testDeleteMedicationById() throws Exception {
    mockMvc.perform(delete(BASE_PATH + MEDICATION_BASE_URL + "/" + MEDICATION_ID)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    verify(medicationService).deleteMedication(any());
  }
}
