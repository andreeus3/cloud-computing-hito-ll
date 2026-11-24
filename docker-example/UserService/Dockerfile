FROM eclipse-temurin:21-jre-alpine
LABEL authors="ivanandres"

WORKDIR /app
COPY ./target/UserService-0.0.1-SNAPSHOT.jar .

EXPOSE 8091

# Run the Java application
CMD ["java", "-jar", "UserService-0.0.1-SNAPSHOT.jar"]