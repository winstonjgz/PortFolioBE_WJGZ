FROM amazoncorretto:11-alpine-jdk
MAINTAINER WJGZ
COPY target/WJGZ-0.0.1-SNAPSHOT.jar beportfolio-app.jar
ENTRYPOINT ["java","-jar","/beportfolio-app.jar"] 