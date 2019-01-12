### Build ###
FROM maven:3.6.0-jdk-11-slim as BUILDER
WORKDIR /
COPY pom.xml src/ frontend/ ./
RUN mvn package

### Run ###
FROM openjdk:13-jdk-alpine
WORKDIR /
COPY --from=BUILDER /target/prescriptions*.jar /prescriptions.jar
EXPOSE 8080
CMD ["java","-jar","prescriptions.jar"]