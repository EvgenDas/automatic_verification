FROM openjdk:17-jdk
EXPOSE 8091
COPY *.jar automatic_verification-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "automatic_verification-0.0.1-SNAPSHOT.jar"]