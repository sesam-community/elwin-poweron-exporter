FROM openjdk:8-jdk-alpine
COPY ./target/demo-0.0.1-SNAPSHOT.jar /opt/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/demo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8090:8090