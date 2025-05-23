# 1️⃣ Use official Maven image to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies first (to leverage Docker cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the full source code
COPY src ./src

# Package the application (will generate target/*.jar)
RUN mvn clean package -DskipTests

# 2️⃣ Use a lightweight JDK image to run the app
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the jar from the builder stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Render uses dynamic ports, but this is optional)
EXPOSE 8080

# Run the app, binding to PORT env var if provided by Render
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
