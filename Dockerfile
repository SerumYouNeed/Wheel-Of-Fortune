FROM eclipse-temurin:20-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/wheel-of-fortune-1.0-SNAPSHOT.jar"]
