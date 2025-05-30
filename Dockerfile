FROM openjdk

WORKDIR /app

COPY target/NetWorkDavid-0.0.1-SNAPSHOT.jar /app/NetWorkDavid-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "NetWorkDavid-0.0.1-SNAPSHOT.jar"]