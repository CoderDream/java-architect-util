## Docker 安装 Kettle







```
c2925ced495a


"/var/lib/docker/overlay2/2549b60b9c7b0303b01844b3fe4ca0a9632383b425441708f9060ef502b04272/merged"
```





```
cd /var/lib/docker/overlay2/2549b60b9c7b0303b01844b3fe4ca0a9632383b425441708f9060ef502b04272/merged
```



#### 找到 MergeDir

 ![image-20221002151120360](D:\04_GitHub\java-architect-util\kettle\assets\image-20221002151120360.png)



 ![image-20221002151013589](assets\image-20221002151013589.png)





```
cd 

```



 ![image-20221002151346593](assets\image-20221002151346593.png)





### MySQL驱动



#### 已安装好驱动

![image-20221002154208425](D:\04_GitHub\java-architect-util\kettle\assets\image-20221002154208425.png)



#### 拷贝驱动到对应的路径

```



cd /var/lib/docker/overlay2/2549b60b9c7b0303b01844b3fe4ca0a9632383b425441708f9060ef502b04272/diff/usr/local/tomcat/lib/
cd /var/lib/docker/overlay2/2549b60b9c7b0303b01844b3fe4ca0a9632383b425441708f9060ef502b04272/merged/usr/local/tomcat/lib/

cp /home/mysql-connector-java-5.1.49-bin.jar /var/lib/docker/overlay2/2549b60b9c7b0303b01844b3fe4ca0a9632383b425441708f9060ef502b04272/diff/usr/local/tomcat/lib/mysql-connector-java-5.1.49-bin.jar
cp /home/mysql-connector-java-5.1.49-bin.jar /var/lib/docker/overlay2/2549b60b9c7b0303b01844b3fe4ca0a9632383b425441708f9060ef502b04272/merged/usr/local/tomcat/lib/mysql-connector-java-5.1.49-bin.jar
```



 ![image-20221002155735233](D:\04_GitHub\java-architect-util\kettle\assets\image-20221002155735233.png)



### 连接数据库





 ![image-20221002162023375](assets\image-20221002162023375.png)





 ![image-20221002160442081](D:\04_GitHub\java-architect-util\kettle\assets\image-20221002160442081.png)



 ![image-20221002161535523](assets\image-20221002161535523.png)



 ![image-20221002161514665](assets\image-20221002161514665.png)



 ![image-20221002161600805](assets\image-20221002161600805.png)





 ![image-20221002161638581](assets\image-20221002161638581.png)



 ![image-20221002161734707](D:\04_GitHub\java-architect-util\kettle\assets\image-20221002161734707.png)



 ![image-20221002161811082](assets\image-20221002161811082.png)











































































# END