FROM azul/zulu-openjdk-alpine
MAINTAINER Wang Qing <wq237wq@gmail.com>
ADD target/notebook-service-1.1.jar notebook.jar
ENTRYPOINT ["java", "-jar", "/notebook.jar"]
EXPOSE 1111