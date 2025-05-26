FROM openjdk:17-jdk-slim
COPY target/chessmaster-0.0.1-SNAPSHOT.jar /app/chessmaster.jar
ENTRYPOINT [ "java", "-jar", "/app/chessmaster.jar" ]
