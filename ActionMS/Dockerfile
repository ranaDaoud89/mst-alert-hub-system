FROM openjdk:17-jdk

COPY ./target/ActionMS-0.0.1-SNAPSHOT.jar /usr/app/ 

WORKDIR /usr/app  
RUN sh -c 'touch ActionMS-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/usr/app/ActionMS-0.0.1-SNAPSHOT.jar"]