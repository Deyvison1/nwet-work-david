FROM maven:3.9.8-eclipse-temurin-alpine as build

COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean verify

FROM maven:3.9.8-eclipse-temurin:17-jre-alpine

COPY --from=builder target/*.jar NetWorkDavid-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "NetWorkDavid-0.0.1-SNAPSHOT.jar"]