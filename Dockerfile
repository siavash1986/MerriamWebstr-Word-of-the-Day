FROM openjdk:11-jre-slim
USER 1006
VOLUME /tmp
COPY build/libs/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]