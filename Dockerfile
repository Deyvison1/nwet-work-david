FROM openjdk:17-jdk-slim

WORKDIR /app

COPY /target/*.jar.original app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
