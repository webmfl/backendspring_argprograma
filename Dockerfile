FROM amazoncorretto:11-alpine-jdk 
MAINTAINER webmfl
COPY target/crud-0.0.1-SNAPSHOT.jar Ncrud-0.0.1-SNAPSHOT.jar 
ENTRYPOINT ["java","-jar","/crud-0.0.1-SNAPSHOT.jar"]
