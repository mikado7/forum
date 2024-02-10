FROM openjdk:17
ARG JAR_FILE=targer/*.jar
COPY forum.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]