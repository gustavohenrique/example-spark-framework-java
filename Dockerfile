FROM maven:3-jdk-8-alpine
MAINTAINER Gustavo Henrique <iam@gustavohenrique.net>

COPY $PWD /app
VOLUME /app
WORKDIR /app
EXPOSE 9999

RUN bash -c "mvn clean package -DskipTests=true"

CMD ["java", "-jar", "/app/target/spotippos-1.0-SNAPSHOT-jar-with-dependencies.jar"]
