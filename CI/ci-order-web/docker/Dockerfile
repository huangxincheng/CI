FROM fabric8/java-alpine-openjdk8-jdk
#FROM fabric8/java-jboss-openjdk8-jdk
#FROM openjdk:8-jdk-alpine
MAINTAINER huangxincheng 249270087@qq.com
VOLUME /tmp
EXPOSE 8082
RUN mkdir -p /home/ci && chmod 755 /home/ci
COPY ./ci-order-web.jar /home/ci
ENTRYPOINT ["java","-jar","/home/ci/ci-order-web.jar"]
