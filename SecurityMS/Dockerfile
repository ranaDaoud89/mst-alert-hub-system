FROM openjdk:17-jdk

COPY ./target/SecurityMS-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app  
RUN sh -c 'touch SecurityMS-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/usr/app/SecurityMS-0.0.1-SNAPSHOT.jar"]