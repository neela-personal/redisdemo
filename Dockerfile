# Use a base image with Java 17
FROM eclipse-temurin:17-jdk-focal

# Open port 8080 from docker image where springboot server is running
WORKDIR /

# Copy the JAR file into the container
COPY target/redisapp-1.0-SNAPSHOT.jar redisapp.jar

EXPOSE 8080

# Command to execute when the container starts
CMD ["java", "-jar", "redisapp.jar"]
