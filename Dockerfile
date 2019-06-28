FROM openjdk:11-jre-slim

COPY /jmx/jmx_prometheus_javaagent-0.9.jar /jmx/jmx_prometheus_javaagent-0.9.jar
COPY /jmx/config.yaml /jmx/config.yaml
RUN chgrp -R 0 /jmx && chmod -R g=u /jmx

USER 1006
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar
EXPOSE 8080 8089

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dcom.sun.management.jmxremote","-Dcom.sun.management.jmxremote.port=9999","-Xms128m","-Xmx256m","-Dcom.sun.management.jmxremote.authenticate=false","-Dcom.sun.management.jmxremote.ssl=false","-javaagent:/jmx/jmx_prometheus_javaagent-0.9.jar=8089:/jmx/config.yaml","-jar" ,"app.jar"]

