FROM openjdk:8-jre-alpine
ARG JAR_FILE=build/libs/*.jar

WORKDIR /app
COPY ${JAR_FILE} worker-app.jar
EXPOSE 8080

CMD ["java", "-jar", "worker-app.jar"]