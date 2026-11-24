FROM eclipse-temurin:21-jre-alpine
LABEL authors="ivanandres"

WORKDIR /app
COPY ./target/BusService-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

# Run the Java application
CMD ["java", "-jar", "BusService-0.0.1-SNAPSHOT.jar"]