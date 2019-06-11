# 镜像小，启动快， 当无法使用arthas
#FROM fabric8/java-alpine-openjdk8-jdk
#FROM fabric8/java-jboss-openjdk8-jdk
#FROM openjdk:8-jdk-alpine
FROM openjdk:8-jdk-alpine
MAINTAINER huangxincheng 249270087@qq.com
VOLUME /tmp
EXPOSE 8081
RUN mkdir -p /home/ci && chmod 755 /home/ci
# 拷贝本地的ci-web.jar到镜像内部的/home/ci目录下
COPY ./ci-user-web.jar /home/ci
# 拷贝远程镜像的arthas的/opt/arthas到镜像内部的/opt/arthas种
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
ENTRYPOINT ["/tini", "--"]
#CMD ["/your/program", "-and", "-its", "arguments"]
CMD ["java","-jar","/home/ci/ci-user-web.jar"]