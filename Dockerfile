# Use an official JDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy built jar file into the container
COPY target/notice-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Define default command
ENTRYPOINT ["java", "-jar", "app.jar"]
