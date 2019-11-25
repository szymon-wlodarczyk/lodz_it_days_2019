FROM openjdk:11-jdk-slim
EXPOSE 8080

COPY build/libs/events-processing-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT exec java -jar /app.jar