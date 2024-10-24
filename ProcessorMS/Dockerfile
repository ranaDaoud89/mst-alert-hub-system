FROM openjdk:17-jdk

COPY ./target/MetricMS-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app  
RUN sh -c 'touch MetricMS-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/usr/app/MetricMS-0.0.1-SNAPSHOT.jar"]