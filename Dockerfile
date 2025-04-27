# Step 1: Use openjdk image (with Maven preinstalled)
FROM maven:3.8.6-openjdk-17-slim AS build

# Step 2: Copy your code into the container
WORKDIR /app
COPY . /app

# Step 3: Build the project using Maven
RUN mvn clean install

# Step 4: Run the JAR file
ENTRYPOINT ["java", "-jar", "target/MiniProject2-0.0.1-SNAPSHOT.jar"]
