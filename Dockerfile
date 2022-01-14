FROM openjdk:11.0.12-jdk-slim
EXPOSE 8081
ADD target/power-plant-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

