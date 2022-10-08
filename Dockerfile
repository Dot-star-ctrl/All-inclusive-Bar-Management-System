FROM gradle:jdk11 as builder


COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle clean build

FROM openjdk:11-jre-slim as finalApp

ENTRYPOINT ["sudo", "mkdir", "/app/"]

WORKDIR app

#COPY --from=builder /home/gradle/src/build/libs/Books-0.0.1-SNAPSHOT.jar /app

COPY --from=builder /home/gradle/src/build/libs/barManagementSystem.jar /app

EXPOSE 9090

#ENTRYPOINT ["java", "-jar", "/app/barManagementSystem.jar"]

# Start with a base image containing Java runtime

ENTRYPOINT ["java","-jar","./build/libs/barManagementSystem.jar"]