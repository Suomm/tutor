# 基础镜像
FROM docker.elastic.co/elasticsearch/elasticsearch:7.6.2
# 创建分词器目录
RUN mkdir -p /usr/share/elasticsearch/plugins/ik
# 添加分词器插件
ADD ./plugin/elasticsearch-analysis-ik-7.6.2.zip /usr/share/elasticsearch/plugins/ik