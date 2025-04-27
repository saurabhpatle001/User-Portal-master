# Step 1: Use openjdk image
FROM openjdk:17-jdk-slim AS build

# Step 2: Install Maven
RUN apt-get update && apt-get install -y maven

# Step 3: Copy your code into the container
WORKDIR /app
COPY . /app

# Step 4: Build the project using Maven
RUN mvn clean install

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "target/MiniProject2-0.0.1-SNAPSHOT.jar"]
