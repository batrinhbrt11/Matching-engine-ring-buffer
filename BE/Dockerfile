# Use Maven with OpenJDK 17 as the base image
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml ./
COPY src ./src

# Package the application, skipping tests
RUN mvn clean package -DskipTests

# Use OpenJDK 17 for the final image
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/starter-0.0.1-SNAPSHOT.jar ./app.jar

# Run the JAR file
CMD ["java", "-jar", "app.jar"]
