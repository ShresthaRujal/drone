package com.rujal.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.rujal")
public class DronesApplication {

  public static void main(String[] args) {
    SpringApplication.run(DronesApplication.class, args);
  }

}
