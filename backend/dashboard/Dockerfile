# Use OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file from target folder into container
COPY target/*.jar app.jar

# Expose port (default Spring Boot port is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
