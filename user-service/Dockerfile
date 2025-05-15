# 1) Build stage
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
COPY target/user-service-1.0-SNAPSHOT.jar ./app.jar
COPY wallet ./wallet

EXPOSE 8080
ENTRYPOINT ["java","-jar","./app.jar"]

