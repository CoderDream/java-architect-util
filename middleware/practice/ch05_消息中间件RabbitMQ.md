### 第5章 消息中间件RabbitMQ
* 5.1　RabbitMQ概述

  * 5.1.1　认识RabbitMQ

    <div align="center">
    	<img src=images\ch05\0501.jpg width=60% />
		</br>
		<span>图5.1 RabbitMQ的官方介绍</span>
    </div>

  * 5.1.2　典型应用场景介绍

    <div align="center">
    	<img src=images\ch05\0502.jpg width=80% />
		</br>
		<span>图5.2 RabbitMQ的特性</span>
    </div>
    
    <div align="center">
    	<img src=images\ch05\0503.jpg width=80% />
		</br>
		<span>图5.3 传统的企业级应用系统用户注册流程</span>
    </div>
	
	<div align="center">
    	<img src=images\ch05\0504.jpg width=80% />
		</br>
		<span>图5.4 引入RabbitMQ消息中间件后用户注册的流程</span>
    </div>
  
     <div align="center">
    	<img src=images\ch05\0505.jpg width=80% />
		</br>
		<span>图5.5 商城商品抢购活动的传统处理流程</span>
    </div>
  
    <div align="center">
    	<img src=images\ch05\0506.jpg width=80% />
		</br>
		<span>图5.6 引入RabbitMQ后系统的整体处理流程</span>
    </div>

    <div align="center">
    	<img src=images\ch05\0507.jpg width=80% />
		</br>
		<span>图5.7 抢票成功后30分钟内未付款的传统处理流程</span>
    </div>
  
    <div align="center">
    	<img src=images\ch05\0508.jpg width=80% />
		</br>
		<span>图5.8 抢票成功后30分钟内未付款的优化处理流程</span>
		</br>
    </div>

  * 5.1.3　RabbitMQ后端控制台介绍
    
    <div align="center">
    	<img src=images\ch05\0509.jpg width=80% />
		</br>
		<span>图5.9 RabbitMQ的后端管理控制台</span>
		</br>
    </div>
    
    <div align="center">
    	<img src=images\ch05\0510.jpg width=80% />
		</br>
		<span>图5.10 RabbitMQ的队列详情</span>
		</br>
    </div>
  * 5.1.4　基于Spring的事件驱动模型实战
    
    <div align="center">
    	<img src=images\ch05\0511.jpg width=100% />
		</br>
		<span>图5.11 基于Spring的事件驱动模型图</span>
    </div>
    
    <div align="center">
    	<img src=images\ch05\0512.jpg width=60% />
		</br>
		<span>图5.12 基于Spring的事件驱动模型代码文件位置</span>
    </div>
    
    <div align="center">
    	<img src=images\ch05\0513.jpg width=80% />
		</br>
		<span>图5.13 基于Spring的事件驱动模型代码运行结果</span>
    </div>
  
* 5.2　Spring Boot项目整合RabbitMQ　169
  * 5.2.1　RabbitMQ相关词汇介绍　170
    <div align="center">
    	<img src=images\ch05\0514.jpg width=80% />
		</br>
		<span>图5.14 邮局的几大核心要素</span>
    </div>·
	
	<div align="center">
    	<img src=images\ch05\0515.jpg width=80% />
		</br>
		<span>图5.15 RabbitMQ的基本消息模型图</span>
    </div>
  * 5.2.2　Spring Boot项目整合RabbitMQ　171
  * 5.2.3　自定义注入配置Bean相关组件　172

  
  * 5.2.4　RabbitMQ发送和接收消息实战　177
  
    <div align="center">
    	<img src=images\ch05\0516.jpg width=80% />
		</br>
		<span>图5.16 RabbitMQ基本消息模型目录结构图</span>
    </div>
	
	<div align="center">
    	<img src=images\ch05\0517.jpg width=80% />
		</br>
		<span>图5.17 RabbitMQ基本消息模型单元测试方法运行结果</span>
    </div>
	
	<div align="center">
    	<img src=images\ch05\0518.jpg width=80% />
		</br>
		<span>图5.18 在RabbitMQ后端控制台查看创建的基本消息模型</span>
    </div>
  * 5.2.5　其他发送和接收消息方式实战　184
    <div align="center">
    	<img src=images\ch05\0519.jpg width=80% />
		</br>
		<span>图5.19 输出结果</span>
    </div>
	
	<div align="center">
    	<img src=images\ch05\0520.jpg width=80% />
		</br>
		<span>图5.20 进入RabbitMQ后端控制台查看队列列表</span>
    </div>
	
	<div align="center">
    	<img src=images\ch05\0521.jpg width=80% />
		</br>
		<span>图5.21 在RabbitMQ后端控制台查看队列详情</span>
    </div>
  
* 5.3　RabbitMQ多种消息模型实战　191
  * 5.3.1　基于FanoutExchange的消息模型实战　191
  * 5.3.2　基于DirectExchange的消息模型实战　201
  * 5.3.3　基于TopicExchange的消息模型实战　209
  
* 5.4　RabbitMQ确认消费机制　218
  * 5.4.1　消息高可用和确认消费机制　218
  * 5.4.2　常见确认消费模式介绍　221
  * 5.4.3　基于自动确认消费模式实战　223
  * 5.4.4　基于手动确认消费模式实战　232
  
* 5.5　典型应用场景实战之用户登录成功写日志　240
  * 5.5.1　整体业务流程介绍与分析　241
  * 5.5.2　数据库设计　242
  * 5.5.3　开发环境搭建　254
  * 5.5.4　基于TopicExchange构建日志消息模型　259
  * 5.5.5　异步发送和接收登录日志消息实战　261
  * 5.5.6　整体业务模块自测实战　267
  
* 5.6　总结　270









# END