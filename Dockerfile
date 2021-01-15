FROM maven:3.6.3-openjdk-8 AS build

COPY . /app
WORKDIR /app
# RUN find .
RUN mvn -DskipTests clean package -B

FROM openjdk:8-jre-stretch
COPY --from=build /app/target/internal-control-0.0.1-SNAPSHOT.jar /app.jar

CMD java -Djava.security.egd=file:/dev/./urandom -jar -Xmx128m -Xss256k /app.jar