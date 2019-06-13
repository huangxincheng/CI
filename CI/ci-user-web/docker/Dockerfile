# 镜像小，启动快， 当无法使用arthas
#FROM fabric8/java-alpine-openjdk8-jdk
#FROM fabric8/java-jboss-openjdk8-jdk
#FROM openjdk:8-jdk-alpine
FROM registry-vpc.cn-shenzhen.aliyuncs.com/huangxincheng/java-alpine-openjdk8-jdk
MAINTAINER huangxincheng 249270087@qq.com
VOLUME /tmp
EXPOSE 8081
RUN mkdir -p /home/ci && chmod 755 /home/ci
COPY ./ci-user-web.jar /home/ci
ENTRYPOINT ["/sbin/tini", "--"]
CMD ["java","-Xmx512m -Xms512m","-jar","/home/ci/ci-user-web.jar"]