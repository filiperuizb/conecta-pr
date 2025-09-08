FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .

COPY common/pom.xml common/
COPY core/pom.xml core/
COPY endpoint/pom.xml endpoint/

RUN mvn dependency:go-offline -B

COPY common/src common/src
COPY core/src core/src
COPY endpoint/src endpoint/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jre-slim

WORKDIR /app

COPY --from=build /app/endpoint/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
