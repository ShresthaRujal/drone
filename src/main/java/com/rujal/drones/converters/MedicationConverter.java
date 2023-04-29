package com.rujal.drones.converters;

import com.rujal.drones.domain.Medication;
import com.rujal.drones.dto.MedicationDTO;

public class MedicationConverter implements BaseConverter<Medication, MedicationDTO> {

  @Override
  public Medication fromDto(MedicationDTO dto) {
    Medication medication = new Medication();
    medication.setId(dto.getId());
    medication.setWeight(dto.getWeight());
    medication.setCode(dto.getCode());
    medication.setName(dto.getName());
    medication.setImage(dto.getImage());
    return medication;
  }

  @Override
  public MedicationDTO fromEntity(Medication entity) {
    MedicationDTO medicationDTO = new MedicationDTO();
    medicationDTO.setId(entity.getId());
    medicationDTO.setWeight(entity.getWeight());
    medicationDTO.setCode(entity.getCode());
    medicationDTO.setName(entity.getName());
    medicationDTO.setImage(entity.getImage());
    return medicationDTO;
  }
}
