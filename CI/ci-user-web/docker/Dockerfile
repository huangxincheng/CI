# 镜像小，启动快， 当无法使用arthas
#FROM fabric8/java-alpine-openjdk8-jdk
#FROM fabric8/java-jboss-openjdk8-jdk
#FROM openjdk:8-jdk-alpine
FROM openjdk:8-jdk-alpine
MAINTAINER huangxincheng 249270087@qq.com
VOLUME /tmp
EXPOSE 8081
RUN mkdir -p /home/ci && chmod 755 /home/ci
COPY ./ci-user-web.jar /home/ci
RUN apk add --no-cache tini
ENTRYPOINT ["/sbin/tini", "--"]
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
ENTRYPOINT ["java","-jar","/home/ci/ci-user-web.jar"]