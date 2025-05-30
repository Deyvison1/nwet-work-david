FROM openjdk:17-jdk-alpine

EXPOSE 8080

WORKDIR /app

COPY ./target/*.jar.original app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
