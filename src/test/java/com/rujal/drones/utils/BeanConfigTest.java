package com.rujal.drones.utils;

import com.rujal.drones.repository.DroneRepository;
import com.rujal.drones.repository.MedicationRepository;
import com.rujal.drones.service.DroneService;
import com.rujal.drones.service.MedicationService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
    "SERVER_PORT=8080",
    "DB_HOST=localhost",
    "DB_DATABASE=drones",
    "DB_USERNAME=postgres",
    "DB_USERNAME=postgres"},
    locations = "/ValidationMessages_en.properties"
)
public abstract class BeanConfigTest extends AppDataTest{

  public static final String BASE_PATH = "http://localhost:8080/";
  public static final String APPLICATION_JSON = "application/json";
  @MockBean
  protected MedicationRepository medicationRepository;
  @MockBean
  protected DroneRepository droneRepository;
  @MockBean
  protected MedicationService medicationService;
  @MockBean
  protected DroneService droneService;

}
