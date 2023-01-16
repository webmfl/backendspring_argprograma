FROM amazoncorretto:11-alpine-jdk 
MAINTAINER webmfl 
COPY target/backend_martinlazo.jar backend_martinlazo.jar 
ENTRYPOINT ["java","-jar","/backens_martinlazo.jar"]