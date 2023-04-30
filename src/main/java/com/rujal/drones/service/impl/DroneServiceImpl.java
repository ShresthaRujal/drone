package com.rujal.drones.service.impl;

import static com.rujal.drones.utils.DroneUtils.exceptionDroneNotFound;

import com.rujal.drones.converters.DroneConverter;
import com.rujal.drones.domain.Drone;
import com.rujal.drones.dto.DroneDTO;
import com.rujal.drones.repository.DroneRepository;
import com.rujal.drones.service.DroneService;
import com.rujal.drones.utils.NullAwareBeanUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImpl extends DroneConverter implements DroneService {

  private static final Logger LOG = LoggerFactory.getLogger(DroneServiceImpl.class);
  private final DroneRepository droneRepository;

  public DroneServiceImpl (DroneRepository droneRepository) {
    this.droneRepository = droneRepository;
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
}