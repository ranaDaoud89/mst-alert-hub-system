FROM openjdk:17-jdk

COPY ./target/SMSNotificationMS-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app  
RUN sh -c 'touch MailNotificationMS-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/usr/app/SMSNotificationMS-0.0.1-SNAPSHOT.jar"]