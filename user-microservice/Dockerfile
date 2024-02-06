FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR app
COPY src src
COPY pom.xml pom.xml
RUN mvn clean package &&  \
    java -Djarmode=layertools -jar target/user-microservice-0.0.1-SNAPSHOT.jar extract --destination target/extracted

FROM eclipse-temurin:17-jre
ARG JAR_FILE=user-microservice-0.0.1-SNAPSHOT.jar
WORKDIR app

COPY --from=build app/target/extracted/dependencies/ ./
COPY --from=build app/target/extracted/spring-boot-loader/ ./
COPY --from=build app/target/extracted/snapshot-dependencies/ ./
COPY --from=build app/target/extracted/application/ ./

ENTRYPOINT exec java org.springframework.boot.loader.launch.JarLauncher ${0} ${@}