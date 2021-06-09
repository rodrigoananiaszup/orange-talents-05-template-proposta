## Run Image
FROM openjdk:11
COPY target/proposta.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]