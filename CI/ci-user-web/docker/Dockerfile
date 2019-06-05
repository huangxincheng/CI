FROM fabric8/java-jboss-openjdk8-jdk
#FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ci-user-web.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8081