FROM eclipse-temurin:25-jre-ubi10-minimal

RUN mkdir -p /app

COPY ./target/accrdyn-web-api-0.0.1-SNAPSHOT.jar /app/accrdyn.jar

ENTRYPOINT java -jar /app/accrdyn.jar