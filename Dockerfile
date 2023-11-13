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
COPY jar/SkiStationProject.jar SkiStationProject.jar

# Expose the port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "SkiStationProject.jar"]
