# Drones
[![build](https://github.com/ShresthaRujal/drone/actions/workflows/build-test.yml/badge.svg)](https://github.com/ShresthaRujal/drone/actions/workflows/build-test.yml)

This is a simple **[Spring Boot](https://spring.io/)** app that helps in delivery of small
Medication items that are (urgently) needed in locations with difficult access
The app contains REST API that allows to communicate with the drones.
The specific communication with the drone is not included within this app.

## Code and Format
[Google Java Code Format](https://google.github.io/styleguide/javaguide.html) 

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)
- [PostgreSQL](https://www.postgresql.org/download/) 

## Quick start

### Clone the repository

```
git clone git@github.com:ShresthaRujal/drone.git
```

## Running the application locally

There are several ways to run a Spring Boot application on a machine.

One way is to execute the `main` method in the `com.rujal.drones.DronesApplication` class from IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

### Run Using Docker
#### 1. Build application

```shell
mvn clean package
```
#### 2. Run docker-compose
```shell
docker-compose up --build
```
**What happens:**

1.Starts Postgresql and waits up to 15 seconds for it to finish

2.Starts Spring boot application which populates database with some test data

### Test

Now that we have everything set up and ready to go, Let's take a look at our Swagger UI and try access the Drone API

We can access the Swagger UI locally:
```
http://localhost:8080/swagger-ui/index.html
```
**How to use Swagger to send a request?**

Let try fetching all Available Drones for Loading Medication, We can send a **'GET'** request to **/drones/all-idle-drones**
```
http://localhost:8080/swagger-ui/index.html#/Drone/checkAvailableDrones
```
Here, click on the **Execute button

**Note:** The specific communication with the drone is not included within this app.
