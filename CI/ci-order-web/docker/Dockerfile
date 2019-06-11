FROM fabric8/java-alpine-openjdk8-jdk
#FROM fabric8/java-jboss-openjdk8-jdk
#FROM openjdk:8-jdk-alpine
MAINTAINER huangxincheng 249270087@qq.com
VOLUME /tmp
EXPOSE 8082
RUN mkdir -p /home/ci && chmod 755 /home/ci
COPY ./ci-order-web.jar /home/ci
# 无arthas版
#ENTRYPOINT ["java","-jar","/home/ci/ci-order-web.jar"]


# 有arthas版本
# 拷贝远程镜像的arthas的/opt/arthas到镜像内部的/opt/arthas种
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
RUN apk add --no-cache tini
ENTRYPOINT ["/sbin/tini", "--"]
#CMD ["/your/program", "-and", "-its", "arguments"]
CMD ["java","-jar","/home/ci/ci-order-web.jar"]