# 基础镜像
FROM openjdk:8-jre
# 挂载目录
VOLUME /home/tutor
# 创建目录
RUN mkdir -p /home/tutor
# 指定路径
WORKDIR /home/tutor
# 复制 jar 文件到路径
COPY ./jar/tutor-admin-*.jar /home/tutor/tutor-admin.jar
# 复制域名证书到工作目录
COPY ./cert/*.crt /home/tutor/tjnu_edu_cn.crt
# 安装证书到 JAVA
RUN sh -c '/bin/echo -e "y" | keytool -import \
    -trustcacerts \
    -alias tjnu_edu_cn \
    -file /home/tutor/tjnu_edu_cn.crt \
    -keystore /usr/local/openjdk-8/lib/security/cacerts \
    -storepass changeit'
# 启动后台服务
ENTRYPOINT ["java", "-jar", "tutor-admin.jar"]