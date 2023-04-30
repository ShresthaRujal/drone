package com.rujal.drones.service.impl;

import static com.rujal.drones.domain.EventType.UPDATE_DRONE_BATTERY;
import static com.rujal.drones.utils.DroneUtils.exceptionDroneNotAvailable;
import static com.rujal.drones.utils.DroneUtils.exceptionDroneNotFound;
import static com.rujal.drones.utils.DroneUtils.updateState;
import static com.rujal.drones.utils.HistoryUtils.createHistory;
import static java.util.stream.Collectors.toMap;

import com.rujal.drones.converters.DroneConverter;
import com.rujal.drones.domain.Drone;
import com.rujal.drones.domain.Medication;
import com.rujal.drones.dto.DroneDTO;
import com.rujal.drones.repository.DroneRepository;
import com.rujal.drones.service.DroneSDKService;
import com.rujal.drones.service.DroneService;
import com.rujal.drones.service.HistoryService;
import com.rujal.drones.service.MedicationService;
import com.rujal.drones.utils.DroneUtils;
import com.rujal.drones.utils.NullAwareBeanUtils;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImpl extends DroneConverter implements DroneService {

  private static final Logger LOG = LoggerFactory.getLogger(DroneServiceImpl.class);
  private final DroneRepository droneRepository;
  private final MedicationService medicationService;
  private final HistoryService historyService;
  private final DroneSDKService droneSDKService;

  public DroneServiceImpl (DroneRepository droneRepository, MedicationService medicationService,
      HistoryService historyService, DroneSDKService droneSDKService) {
    this.droneRepository = droneRepository;
    this.medicationService = medicationService;
    this.historyService = historyService;
    this.droneSDKService = droneSDKService;
  }

  @Override
  public DroneDTO addDrone(DroneDTO droneDTO) {
    LOG.info("Creating new entry of Drone : {}", droneDTO.getSerialNumber());
    return fromEntity(droneRepository.save(fromDto(droneDTO)));
  }

  @Override
  public DroneDTO updateDrone(DroneDTO droneDTO) {
    LOG.info("Fetching Drone by Id : {}", droneDTO.getId());
    Drone drone = droneRepository.findById(droneDTO.getId())
        .orElseThrow(exceptionDroneNotFound(droneDTO.getId()));
    LOG.info("Updating Drone with Serial Number : {}", drone.getSerialNumber());
    new NullAwareBeanUtils().copyProperties(drone, fromDto(droneDTO));
    return fromEntity(droneRepository.save(drone));
  }

  @Override
  @Transactional
  public DroneDTO findDroneById(Long id) {
    LOG.info("Fetching Drone by Id : {}", id);
    return fromEntity(droneRepository.findById(id).orElseThrow(exceptionDroneNotFound(id)));
  }

  @Override
  public void deleteDrone(Long id) {
    LOG.info("Delete Drone : {}", id);
    Drone drone = droneRepository.findById(id).orElseThrow(exceptionDroneNotFound(id));
    droneRepository.delete(drone);
  }

  @Override
  public DroneDTO addMedicationOnDrone(Long droneId, List<Long> medicationIds) {
    LOG.info("Loading Medication on Drone : {}", droneId);
    Drone drone = droneRepository.findById(droneId)
        .filter(DroneUtils::isDroneLoadable)
        .orElseThrow(exceptionDroneNotAvailable(droneId));
    List<Medication> medications = medicationService.findMedicationByIds(medicationIds);
    drone.setMedications(medications);
    return fromEntity(droneRepository.save(drone));
  }

  @Override
  @Transactional
  public void checkAndAuditDroneBatteryLevel() {
    LOG.info("Check Drone Battery at {}", LocalDateTime.now());
    List<Drone> droneList = droneRepository.findAll();
    // Map of SerialNumber and Drone
    Map<String, DroneDTO> mapOfDrone = droneSDKService.getCurrentDroneDetails(fromEntity(droneList))
        .stream().collect(toMap(DroneDTO::getSerialNumber, Function.identity()));
    droneList.forEach(drone -> {
      if (mapOfDrone.containsKey(drone.getSerialNumber())) {
        Drone oldDroneStatus = SerializationUtils.clone(drone);
        BigDecimal currentBatteryCapacity = fromDto(mapOfDrone.get(drone.getSerialNumber()))
            .getBatteryCapacity();
        drone.setBatteryCapacity(currentBatteryCapacity);
        updateState(drone);
        historyService.addChangeHistory(createHistory(oldDroneStatus, drone, UPDATE_DRONE_BATTERY));
      }
    });
  }
}