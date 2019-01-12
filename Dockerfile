### Build ###
FROM maven:3.6.0-jdk-11-slim as BUILDER
COPY . /usr/src/app/
RUN mvn -f /usr/src/app/pom.xml clean package

### Run ###
FROM openjdk:13-jdk-alpine
WORKDIR /
COPY --from=BUILDER /target/prescriptions*.jar /prescriptions.jar
EXPOSE 8080
CMD ["java","-jar","prescriptions.jar"]