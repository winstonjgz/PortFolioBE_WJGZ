FROM amazoncorretto:8-alpine-jdk
MAINTAINER WJGZ
COPY target/WJGZ-0.0.2-SNAPSHOT.jar beportfolio-app.jar
ENTRYPOINT ["java","-jar","/beportfolio-app.jar"] 