FROM openjdk:17-jdk

COPY ./target/LoaderMS-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app  
RUN sh -c 'touch LoaderMS-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/usr/app/LoaderMS-0.0.1-SNAPSHOT.jar"]