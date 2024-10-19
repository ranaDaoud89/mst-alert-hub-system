FROM openjdk:17-jdk

COPY ./target/LoggerMS-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app  
RUN sh -c 'touch LoggerMS-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/usr/app/LoggerMS-0.0.1-SNAPSHOT.jar"]