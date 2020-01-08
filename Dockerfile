FROM java
ADD / //

#FROM maven:3.5-jdk-8-alpine
#RUN mvn package


ENTRYPOINT ["java","-jar", "/target/simple-service-0.0.1-SNAPSHOT.jar"]

