FROM openjdk:8
COPY ./target/usuario-0.0.1-SNAPSHOT-jar-with-dependencies.jar /usr/src/usuario/
WORKDIR /usr/src/usuario
EXPOSE 8080
CMD ["java", "-jar", "usuario-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]