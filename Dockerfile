FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD build/libs/ChannelPizza-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongocontainer/test", "-jar", "/app.jar"]