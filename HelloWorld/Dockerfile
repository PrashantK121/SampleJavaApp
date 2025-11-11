# Use an official OpenJDK runtime as the base image
FROM eclipse-temurin:21.0.5_11-jdk-ubi9-minimal

# Set the maintainer label
LABEL maintainer="prashantkushwaha121@gmail.com"

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=${PROFILE}"]
