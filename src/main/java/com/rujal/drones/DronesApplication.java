package com.rujal.drones;

import static com.rujal.drones.utils.Constants.Commons.BASE_PACKAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = BASE_PACKAGE)
public class DronesApplication {

  public static void main(String[] args) {
    SpringApplication.run(DronesApplication.class, args);
  }

}
