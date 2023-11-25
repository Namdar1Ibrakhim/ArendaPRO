FROM openjdk:17.0.2-jdk
ADD target/ArendaPRO-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", ""]
