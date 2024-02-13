# Use official Maven image as the base image
FROM maven:3.8.3-jdk-11 AS build

# Set working directory in the container
WORKDIR /data/sparqlprov/npcs_base/npcs_rewriting_main

# Copy the Maven project file to the container
COPY pom.xml .

# Download all dependencies. Dependencies will be cached if the pom.xml and dependencies do not change
RUN mvn dependency:go-offline -B

# Copy the rest of the project to the container
COPY src ./src
COPY . .
# Build the project
#RUN mvn package
RUN mvn assembly:assembly -DdescriptorId=jar-with-dependencies
ENTRYPOINT ["java", "-cp", "target/npcs_rewriting_main-0.0.1-SNAPSHOT-jar-with-dependencies.jar", "npcs.App"]
# Stage 2: Use lightweight JDK 11 image for running the application
#FROM openjdk:11-jre-slim

# Set working directory in the container
#WORKDIR /data/sparqlprov/npcs_base/npcs_rewriting_main

# Copy the built JAR file from the previous stage
#COPY --from=build /data/sparqlprov/npcs_base/npcs_rewriting_main/target/*.jar ./app.jar

# Expose the port that the application runs on
#EXPOSE 8080

# Command to run the application
#CMD ["java", "-jar", "app.jar"]
