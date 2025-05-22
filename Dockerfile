# Use official OpenJDK image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy all project files
COPY . .

# Build the project
RUN ./mvnw clean install -DskipTests

# Run the JAR
CMD ["java", "-jar", "target/tracking-api-0.0.1-SNAPSHOT.jar"]


