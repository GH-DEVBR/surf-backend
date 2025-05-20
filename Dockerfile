# Etapa de build
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app
COPY src/main/java .
RUN ./mvnw clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
