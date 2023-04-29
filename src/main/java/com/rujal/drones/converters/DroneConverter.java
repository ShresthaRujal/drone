package com.rujal.drones.converters;

import com.rujal.drones.domain.Drone;
import com.rujal.drones.dto.DroneDTO;

public class DroneConverter implements BaseConverter<Drone, DroneDTO> {

  @Override
  public Drone fromDto(DroneDTO dto) {
    Drone drone = new Drone();
    drone.setId(dto.getId());
    drone.setSerialNumber(dto.getSerialNumber());
    drone.setModel(dto.getModel());
    drone.setState(dto.getState());
    drone.setWeightLimit(dto.getWeightLimit());
    drone.setBatteryCapacity(dto.getBatteryCapacity());
    return drone;
  }

  @Override
  public DroneDTO fromEntity(Drone entity) {
    DroneDTO droneDTO = new DroneDTO();
    droneDTO.setId(entity.getId());
    droneDTO.setSerialNumber(entity.getSerialNumber());
    droneDTO.setModel(entity.getModel());
    droneDTO.setState(entity.getState());
    droneDTO.setWeightLimit(entity.getWeightLimit());
    droneDTO.setBatteryCapacity(entity.getBatteryCapacity());
    droneDTO.setMedications(new MedicationConverter().fromEntity(entity.getMedications()));
    return droneDTO;
  }
}
