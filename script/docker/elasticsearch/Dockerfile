# 基础镜像
FROM docker.elastic.co/elasticsearch/elasticsearch:7.6.2
# 添加分词器插件
COPY ./plugin/elasticsearch-analysis-ik-7.6.2.zip /
# 创建分词器目录
RUN mkdir -p /usr/share/elasticsearch/plugins/ik
# 解压分词器插件
RUN unzip /elasticsearch-analysis-ik-7.6.2.zip -d /usr/share/elasticsearch/plugins/ik