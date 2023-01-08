FROM gradle:7.5.1-jdk8 as builder
USER root
RUN mkdir /microservice
WORKDIR /microservice
COPY . .
RUN gradle :clean :bootJar
RUN mv /microservice/build/libs/spring-boot-microservice-*.jar microservice.jar

FROM openjdk:8-jre-alpine
USER root
WORKDIR /microservice
COPY --from=builder ./microservice/microservice.jar microservice.jar
CMD ["java", "-jar", "microservice.jar"]