FROM openjdk:8-alpine
MAINTAINER hsfy
VOLUME /tmp

COPY target/exam_back_end-1.0.jar /usr/share/back-end-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/share/back-end-service.jar"]