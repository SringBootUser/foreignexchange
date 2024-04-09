# Use the official OpenJDK image as a base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/foreignexchange-0.0.1-SNAPSHOT.jar /app

# Expose the port that your Spring Boot application runs on
EXPOSE 8888

# Define the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "foreignexchange-0.0.1-SNAPSHOT.jar"]