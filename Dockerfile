# Use Java 17 runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/votezy-app.jar app.jar

# Expose port (Render will set PORT dynamically)
EXPOSE $PORT

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
