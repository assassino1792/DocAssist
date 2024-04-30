FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/docassist-1.0-SNAPSHOT.jar /app/docassist.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/docassist.jar"]
