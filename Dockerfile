FROM amazoncorretto:11-alpine-jdk

MAINTAINER Adilton Valaszek <valaszek@gmail.com>

COPY build/libs/bank-0.0.1-SNAPSHOT.jar bank-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/bank-0.0.1-SNAPSHOT.jar"]
