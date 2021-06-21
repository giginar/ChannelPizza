FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD build/libs/channelpizza-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]