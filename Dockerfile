# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-slim AS build
WORKDIR /app

# Copy the pom.xml and download dependencies
# This step is optimized to use the Maven layer cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application into an executable JAR
RUN mvn clean package -DskipTests

# Stage 2: Create the final, smaller runtime image
# Use a smaller JRE image for the final container
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on (default Spring Boot port)
EXPOSE 8080

# Define the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
