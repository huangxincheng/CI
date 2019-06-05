FROM fabric8/java-alpine-openjdk8-jdk
#FROM fabric8/java-jboss-openjdk8-jdk
#FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8082
COPY ci-order-web.jar /home/
ENTRYPOINT ["java","-jar","/home/ci-order-web.jar"]
