FROM java:8
# 维护者
MAINTAINER CoderDream<coderdream@gmail.com>
# 创建目录
RUN mkdir -p /volume1/docker/docker_data/ruoyi
# 指定路径
WORKDIR /volume1/docker/docker_data/ruoyi
# 暴露端口
EXPOSE 8080
# 复制jar文件到路径
COPY ./target/ruoyi-admin.jar /volume1/docker/docker_data/ruoyi/ruoyi-admin.jar
# 设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
# 启动系统服务
ENTRYPOINT ["java","-jar","ruoyi-admin.jar"]
