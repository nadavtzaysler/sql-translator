# Use Maven with Java 21 to build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn -f calcite/pom.xml clean package -DskipTests

# Use a lightweight Java 21 JRE for running the app
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/calcite/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
