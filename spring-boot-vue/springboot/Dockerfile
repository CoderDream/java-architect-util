FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/spring-boot-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
