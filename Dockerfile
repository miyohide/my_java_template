FROM amazoncorretto:21.0.5-al2023-headless

ARG JAR_FILE=build/libs/my_java_template-0.0.1.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
