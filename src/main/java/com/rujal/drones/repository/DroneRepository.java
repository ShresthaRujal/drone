package com.rujal.drones.repository;

import com.rujal.drones.domain.Drone;
import com.rujal.drones.utils.State;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {

  List<Drone> findAllByState(State state);
}
