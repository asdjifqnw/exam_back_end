FROM openjdk:8-jdk-alpine
RUN apk add git maven
RUN git clone https://github.com/hsfy19091/exam_back_end.git
RUN cd exam_back_end&&mvn package
