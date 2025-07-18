#Start with a base image that has Java installed
FROM openjdk:17-jdk-slim

#Information about the maintainer of this Dockerfile
MAINTAINER Vamshi Krishna

#Add the application's jar to the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute the jar file
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

