FROM openjdk:17-jdk

COPY ./target/EvaluationMS-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app  
RUN sh -c 'touch EvaluationMS-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/usr/app/EvaluationMS-0.0.1-SNAPSHOT.jar"]