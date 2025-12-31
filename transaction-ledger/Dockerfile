# ---------- Stage 1: Build ----------
FROM eclipse-temurin:21-jdk AS build

# Set working directory inside container
WORKDIR /app

# Copy Gradle/Maven wrapper and config files first
# (helps Docker cache dependencies)
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Download dependencies
RUN ./gradlew dependencies

# Copy application source code
COPY src src

# Build the Spring Boot application (skip tests for faster build)
RUN ./gradlew bootJar -x test


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre

# Set working directory for runtime container
WORKDIR /app

# Copy only the built jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
