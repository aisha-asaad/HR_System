# Start with a base image with JDK 17 (or the version compatible with your app)
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file (from Maven or Gradle build) into the container
COPY target/*.jar app.jar

# Expose the application port (e.g., 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar","app.jar"]