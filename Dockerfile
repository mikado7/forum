FROM openjdk:17
WORKDIR /app
COPY forum-0.0.1-SNAPSHOT.jar /app

ENV DB_USER=admin \
    DB_URL=jdbc:postgresql://dpg-cmshm98l5elc738qjsu0-a.frankfurt-postgres.render.com:5432/projektzespolowydb \
    DB_PASSWORD=VOUraW6esTjqho8UphAMQRuhnzNkNedg

EXPOSE 10000
CMD ["java", "-jar", "forum-0.0.1-SNAPSHOT.jar"]


