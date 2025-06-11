# --- Build stage ---
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# 1) copy top-level pom so Maven sees the multi-module layout
COPY pom.xml .

# 2) copy all modules declared in the root pom
COPY common-api common-api
COPY user-service user-service
COPY budget-service budget-service

# 3) build budget-service and its dependencies (common-api)
#    (-am will also build any projects budget-service depends on)
RUN mvn -pl budget-service -am clean package -DskipTests

# --- Run stage ---
FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app

# 4) pull in the assembled JAR from the build stage
COPY --from=build /app/budget-service/target/budget-service-1.0-SNAPSHOT.jar ./app.jar

# 5) copy in your Oracle wallet (unzipped under user-service/wallet/)
COPY user-service/wallet wallet

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar"]

