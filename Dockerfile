FROM openjdk:17.0.2-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/ArendaPRO-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
