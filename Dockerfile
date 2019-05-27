FROM openjdk:8-alpine
VOLUME /tmp
COPY target/exam_back_end-1.0.jar /usr/share/myservice/myservice.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/share/myservice/myservice.jar"]