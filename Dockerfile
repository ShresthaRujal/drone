FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/drone.jar
ADD ${JAR_FILE} drone.jar
ENTRYPOINT ["java","-jar","/drone.jar"]