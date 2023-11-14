# Use an official OpenJDK runtime as a parent image
#FROM openjdk:8-jre-alpine

# Set the working directory
#WORKDIR /app

# Copy the JAR file into the container
#COPY target/*.jar SkiStationProject.jar

# Expose the port
#EXPOSE 8080

# Command to run the application
#CMD ["java", "-jar", "SkiStationProject.jar"]
# Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container from the 'jar' directory
#COPY jar/*.jar SkiStationProject.jar
ADD http://192.168.33.10:8081/repository/maven-snapshots/tn/esprit/ds/SkiStationProject/0.0.1-SNAPSHOT/SkiStationProject-${BUILD_NUMBER}.jar SkiStationProject.jar

# Expose the port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "SkiStationProject.jar"]
