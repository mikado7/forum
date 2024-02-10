FROM openjdk:17
WORKDIR /app
COPY forum-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080
CMD ["java", "-jar", "forum-0.0.1-SNAPSHOT.jar"]


