FROM openjdk:17
ADD target/automatic_verification-api-docker.jar automatic_verification-api-docker.jar
ENTRYPOINT ["java", "-jar","automatic_verification-api-docker.jar"]
EXPOSE 8092