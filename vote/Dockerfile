##RUN ./gradlew clean build --no-daemon
FROM openjdk:8-jre-alpine
ARG JAR_FILE=build/libs/*.jar

WORKDIR /app
COPY ${JAR_FILE} vote-app.jar
EXPOSE 8080

CMD ["java", "-jar", "vote-app.jar"]