package com.rujal.drones.service.impl;

import static com.rujal.drones.utils.MessagePropertyConstants.MEDICATION_NOT_FOUND;
import static com.rujal.drones.utils.MessageUtils.getMessage;

import com.rujal.drones.converters.MedicationConverter;
import com.rujal.drones.domain.Medication;
import com.rujal.drones.dto.MedicationDTO;
import com.rujal.drones.exceptions.ResourceNotFoundException;
import com.rujal.drones.repository.MedicationRepository;
import com.rujal.drones.service.MedicationService;
import com.rujal.drones.utils.NullAwareBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MedicationServiceImpl extends MedicationConverter implements MedicationService {

  private static final Logger LOG = LoggerFactory.getLogger(MedicationServiceImpl.class);
  private MedicationRepository medicationRepository;

  public MedicationServiceImpl(MedicationRepository medicationRepository) {
    this.medicationRepository = medicationRepository;
  }

  @Override
  public MedicationDTO addMedication(MedicationDTO medicationDTO) {
    LOG.info("Creating new entry of Medication : {}", medicationDTO.getCode());
    return fromEntity(medicationRepository.save(fromDto(medicationDTO)));
  }

  @Override
  public MedicationDTO updateMedication(MedicationDTO medicationDTO) {
    LOG.info("Fetching Medication by Id : {}", medicationDTO.getId());
    Medication medication = medicationRepository.findById(medicationDTO.getId())
        .orElseThrow(() -> new ResourceNotFoundException(
            getMessage(MEDICATION_NOT_FOUND.getValue(), medicationDTO.getId()))
        );
    new NullAwareBeanUtils().copyProperties(medication, fromDto(medicationDTO));
    return fromEntity(medicationRepository.save(medication));
  }

  @Override
  public MedicationDTO findMedicationById(Long id) {
    LOG.info("Fetching Medication by Id : {}", id);
    return fromEntity(medicationRepository.findById(id)
        .orElseThrow(() ->
            new ResourceNotFoundException(getMessage(MEDICATION_NOT_FOUND.getValue(), id)))
    );
  }

  @Override
  public void deleteMedication(Long id) {
    LOG.info("Deleting Medication : {}", id);
    Medication medication = medicationRepository.findById(id)
        .orElseThrow(() ->
            new ResourceNotFoundException(getMessage(MEDICATION_NOT_FOUND.getValue(), id))
        );
    medicationRepository.delete(medication);
  }
}
