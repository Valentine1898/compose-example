FROM java

ADD /target/simple-service-0.0.1-SNAPSHOT.jar //
RUN mkdir /storage
RUN mkdir /logs

ENTRYPOINT ["java","-jar", "simple-service-0.0.1-SNAPSHOT.jar"]
