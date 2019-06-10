# 镜像小，启动快
FROM fabric8/java-alpine-openjdk8-jdk
#FROM fabric8/java-jboss-openjdk8-jdk
#FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8081
RUN mkdir -p /home/ci && chmod 755 /home/ci
COPY ./target/ci-user-web.jar /home/ci
ENTRYPOINT ["java","-jar","/home/ci/ci-user-web.jar"]
