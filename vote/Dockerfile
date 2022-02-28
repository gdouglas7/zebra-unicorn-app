##RUN ./gradlew clean build --no-daemon
FROM openjdk:8-jre-alpine
ARG JAR_FILE=build/libs/*.jar

WORKDIR /app
COPY ${JAR_FILE} zebra-unicorn-app.jar
EXPOSE 8080

CMD ["java", "-jar", "zebra-unicorn-app.jar"]


#FROM openjdk:8-jre-alpine
#WORKDIR /app
#COPY /build/libs/zebra-unicorn-app-0.0.1-SNAPSHOT.jar .
#EXPOSE 8080
#CMD ["java", "-jar", "zebra-unicorn-app-0.0.1-SNAPSHOT.jar"]


#FROM gradle:7.4.0-jdk8 as builder
#COPY --chown=gradle:gradle . /home/gradle
#WORKDIR /home/gradle
#RUN gradle build --stacktrace --no-daemon
#
#FROM openjdk:8-jre-alpine
#WORKDIR /app
#EXPOSE 8080
#COPY --from=builder /home/gradle/build/libs/zebra-unicorn-app-0.0.1-SNAPSHOT.jar .
#CMD ["java", "-jar", "zebra-unicorn-app-0.0.1-SNAPSHOT.jar"]


#RUN ./gradlew clean installDist --no-daemon && \
#    mv build/install/* /home/ && \
#    rm -r build

#FROM gradle:7.4.0-jdk8 as builder
#COPY --chown=gradle:gradle . /home/gradle
#WORKDIR /home/gradle
#RUN gradle build --stacktrace --no-daemon
#
#FROM openjdk:8-jre-alpine
#WORKDIR /app
#EXPOSE 8080
#COPY --from=builder /home/gradle/build/libs/zebra-unicorn-app-0.0.1-SNAPSHOT.jar .
#CMD ["java", "-jar", "zebra-unicorn-app-0.0.1-SNAPSHOT.jar"]