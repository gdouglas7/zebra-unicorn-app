FROM openjdk:8-jre-alpine
ARG JAR_FILE=build/libs/*.jar
ARG APP_DIR=/app

WORKDIR ${APP_DIR}
COPY ${JAR_FILE} ${APP_DIR}/result-app.jar

RUN addgroup -S appgroup \
    && adduser -S appuser -G appgroup \
    && chown -R appuser ${APP_DIR}

USER appuser
EXPOSE 8080

CMD ["java", "-jar", "result-app.jar"]