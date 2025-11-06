# ===============================
# Stage 1: Build the application
# ===============================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (for faster builds)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# ===============================
# Stage 2: Run the application
# ===============================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port that Spring Boot runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
