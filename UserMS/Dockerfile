FROM openjdk:17-jdk

COPY ./target/UserMS-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app  
RUN sh -c 'touch UserMS-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/usr/app/UserMS-0.0.1-SNAPSHOT.jar"]