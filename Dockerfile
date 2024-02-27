FROM maven:3.8.4-openjdk-17 as build

# Copy your source code into the image
COPY src /home/app/src
COPY pom.xml /home/app

# Package your application
RUN mvn -f /home/app/pom.xml clean package

# Start with a new base image to run your application
FROM openjdk:17

# Copy the JAR file from the previous stage
COPY --from=build /home/app/target/*.jar /usr/local/lib/myapp.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run your app
CMD ["java", "-jar", "/usr/local/lib/myapp.jar"]
