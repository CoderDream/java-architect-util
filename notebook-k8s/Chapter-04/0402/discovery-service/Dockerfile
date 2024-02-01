#指使用jdk.18的java镜像,在容器中安装java.
FROM java:8
#作者
MAINTAINER author
#指向了一个/tmp的目录,springboot内置tomcat默认使用/tmp作为工作目录.
#作用是在主机的/var/lib/docker目录下创建了一个临时文件,并连接到容器的/tmp.
VOLUME /tmp
#将项目的jar文件作为app.jar添加到容器,app.jar就是别名.
ADD target/discovery-service-0.0.1-SNAPSHOT.jar app.jar
#表示在新创建的镜像中执行一些命令,然后把执行的结果提交到当前镜像.这里使用bash或sh都可以,没什么区别,bash完全兼容sh.
RUN bash -c 'touch /app.jar'
#容器暴露端口.
EXPOSE 8761
#应用启动命令,设置启动参数等
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]
