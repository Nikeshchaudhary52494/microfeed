FROM maven:3.9.5-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]