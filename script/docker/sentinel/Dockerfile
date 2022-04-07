# 基础镜像
FROM openjdk:8-jre
# 创建目录
RUN mkdir -p /home/sentinel
# 指定路径
WORKDIR /home/sentinel
# 复制jar文件到路径
COPY ./jar/sentinel-dashboard-1.8.1.jar /home/sentinel/sentinel-dashboard.jar
# 启动认证服务
ENTRYPOINT ["java","-jar","sentinel-dashboard.jar"]