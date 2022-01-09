FROM maven:3.6.3 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/*.jar /opt/app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]