# 基础镜像
FROM ibm-semeru-runtimes:open-11-jre
# 挂载目录
VOLUME /home/tutor
# 创建目录
RUN mkdir -p /home/tutor
# 指定路径
WORKDIR /home/tutor
# 复制 jar 文件到路径
COPY ./jar/tutor-gateway-*.jar /home/tutor/tutor-gateway.jar
# 启动后台服务
ENTRYPOINT ["java", "-jar", "tutor-gateway.jar"]