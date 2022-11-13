## Day01





### 配置文件

* activiti.cfg.xml

```sql
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
						http://www.springframework.org/schema/contex http://www.springframework.org/schema/context/spring-context.xsd
						http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">

    <!--数据源配置dbcp-->
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://192.168.3.4:33016/itcast0711activiti"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>
    <!--activiti单独运行的ProcessEngine配置对象(processEngineConfiguration),使用单独启动方式
        默认情况下：bean的id=processEngineConfiguration
    -->

    <bean id="processEngineConfiguration" class="org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration">
        <!--代表数据源-->
        <property name="dataSource" ref="dataSource"></property>

        <!-- <property name="jdbcDriver" value="com.mysql.jdbc.Driver" />
         <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/activiti" />
         <property name="jdbcUsername" value="root" />
         <property name="jdbcPassword" value="root" />-->
        <!--代表是否生成表结构-->
        <property name="databaseSchemaUpdate" value="true"/>
    </bean>
</beans>
```



```java
public class ActivitiTest {

    @Test
    public void testGenTable(){
        //条件：1.activiti配置文件名称：activiti.cfg.xml   2.bean的id="processEngineConfiguration"
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
}
```

* 生成的表

 ![image-20221113213904024](assets\image-20221113213904024.png)



 ![image-20221113214146721](assets\image-20221113214146721.png)



