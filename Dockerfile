FROM java
ADD / //
ENTRYPOINT ["java","-jar", "/target/simple-service-0.0.1-SNAPSHOT.jar"]

