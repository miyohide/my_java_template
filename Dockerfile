# Build Stage
FROM amazoncorretto:21.0.5-al2023 as build

RUN dnf install findutils -y

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

RUN chmod +x gradlew

RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew build --no-daemon

FROM amazoncorretto:21.0.5-al2023-headless

WORKDIR /app

COPY --from=build /app/build/libs/my_java_template-0.0.1.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
