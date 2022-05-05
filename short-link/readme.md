
### 参考文档
1. [SpringBoot-集成Swagger3](https://www.csdn.net/tags/OtTaMg2sMzMxMTEtYmxvZwO0O0OO0O0O.html)
2. [Java Google Guava](https://geek-docs.com/java/java-tutorial/guava.html#Guava)
3. [【强力推荐】jacoco代码测试覆盖率实战教学全集，7天从入门到精通【理论+实战 赶紧拿走】](https://www.bilibili.com/video/BV1tr4y1i7f1)
4. [SpringBoot通过Jacoco生成用例覆盖率报告（包括Mockito生成测试用例）](https://vegetable-chicken.blog.csdn.net/article/details/86293827)
5. [下载 jmeter 以及历史版本](https://www.cnblogs.com/yongzhuang/p/12125119.html)
6. [全网最全最细的jmeter接口测试教程以及接口测试流程详解](https://www.cnblogs.com/csmashang/p/12762177.html)
7. [jmeter5.2.1教程](https://blog.csdn.net/Alice_whj/article/details/105386987)
8. [Spring Cache一之CacheManager的创建](https://blog.csdn.net/jiachunchun/article/details/90235418)
9. [SpringBoot2.x—SpringCache（1）集成](https://www.jianshu.com/p/d44b1c0f9df0)
10. [SpringBoot2.x—SpringCache（2）使用](https://www.jianshu.com/p/2dc8566dd0a3)
11. [SpringBoot2.x—SpringCache（3） CacheManager源码](https://www.jianshu.com/p/ef8fb285ed72)
12. [JAVA && Spring && SpringBoot2.x — 学习目录](https://www.jianshu.com/p/67fce672e03e)
13. [SpringBoot缓存管理（一） 默认缓存管理](https://www.cnblogs.com/blayn/p/14890973.html)
14. [10进制转62进制 62转10进制](https://blog.csdn.net/qq_33238562/article/details/103184080)
15. [Java实现布隆过滤器(已爬URL过滤)](https://blog.csdn.net/woaigaolaoshi/article/details/51283212)
16. [JAVA布隆过滤器的使用BloomFilter](https://blog.csdn.net/codeissodifficulty/article/details/93980291)
17. [SpringBoot使用Guava做内存缓存](https://www.jianshu.com/p/b859780c1112)
18. [Guava Cache本地缓存在 Spring Boot应用中的实践](https://www.jianshu.com/p/921c588289c7)
19. [SpringBoot 缓存 Caffeine使用](https://blog.csdn.net/qq_33697094/article/details/114968105)
20. [SpringBoot使用内存高性能缓存Caffeine详解](https://blog.51cto.com/u_15067230/2573526)
21. [springboot学习(十一)：缓存Guava的使用](https://blog.csdn.net/sinat_36553913/article/details/85837164)
22. [SpringBoot系列之缓存使用教程 ](https://www.cnblogs.com/mzq123/p/12629142.html)
23. [springboot Caffeine 详解（一篇就明白）](https://blog.csdn.net/dgh112233/article/details/119009366)
24. [Guava Cache用法介绍](https://www.cnblogs.com/fnlingnzb-learner/p/11022152.html)
25. [Guava缓存详解及使用](https://blog.csdn.net/why_still_confused/article/details/107138230)
26. [SpringBoot&Guava缓存（多个示例）](https://github.com/zheng-zy/spring-boot-redis-guava-caffeine-cache)
27. [基于Redis生成递增序号](https://blog.csdn.net/G0_hw/article/details/119269670)


* 56,800,235,584 6位62进制数可以生成560亿个字符串，62的6次方


### 方案
1. [请实现一个「短域名」的系统设计](https://leetcode-cn.com/circle/discuss/EkCOT9/)
1. [短域名方案及实现](https://www.yuque.com/docs/share/17e99d09-c21d-4ad6-a07b-8d2dc7f712dd)
2. [短网址(short URL)系统的原理及其实现](https://segmentfault.com/a/1190000012088345)
3. [短网址(short URL)系统的原理及其实现](https://hufangyun.com/2017/short-url/)
4. [高性能短链设计](https://mp.weixin.qq.com/s/YTrBaERcyjvw7A0Fg2Iegw)
5. [【原创】这可能是东半球最接地气的短链接系统设计 ](https://www.cnblogs.com/rjzheng/p/11827426.html)
6. [JAVA布隆过滤器的使用BloomFilter](https://blog.csdn.net/codeissodifficulty/article/details/93980291)
7. [MurmurHash算法简单介绍](https://www.cnblogs.com/strongmore/p/14493705.html)
8. [短链服务和应用场景介绍-初级入门(一) 连载持续更新中](https://blog.csdn.net/wnn654321/article/details/122021485)



### 参考作业
1.  [作业1](https://github.com/scdt-china/interview-assignments/pull/645/files#diff-4b6fe62a4b3022428a25cdc6d7ea77c3649f822a365e1394f65d903ecc62c328)


### 原地址 
[Java Assignment](https://github.com/scdt-china/interview-assignments/tree/master/java)

@@ -8,6 +8,8 @@

### Assignment

> **Tips: 记得注意仔细审题**
#### 实现短域名服务（细节可以百度/谷歌）

撰写两个 API 接口:
@@ -17,18 +19,19 @@
限制：
- 短域名长度最大为 8 个字符
- 采用SpringBoot，集成Swagger API文档；
- JUnit编写单元测试, 使用Jacoco生成测试报告(测试报告提交截图即刻)；
- JUnit编写单元测试, 使用Jacoco生成测试报告(测试报告提交截图)；
- 映射数据存储在JVM内存即可，防止内存溢出；

**递交作业内容** 
- 源代码(按照生产级的要求编写整洁的代码，使用gitignore过滤掉非必要的提交文件，如class文件)
- Jacoco单元测试覆盖率截图(行覆盖率和分支覆盖率85%+)
- 文档：设计思路、架构设计图以及所做的假设(Markdown格式)
- 文档：完整的设计思路、架构设计图以及所做的假设(Markdown格式)

**加分项** 
- 系统性能测试方案以及测试结果



## Job Description

### 岗位职责
1. 负责公司内部自用产品开发，能够独立的按产品需求进行技术方案设计和编码实现，确保安全、可扩展性、质量和性能;
2. 在负责的业务上有独立的见解和思考，对业务产品具有独立沟通、完善业务需求和识别方案风险的能力;
3. 具有持续优化、追求卓越的激情和能力，能持续关注和学习相关领域的知识，并能使用到工作当中;
4. 具备和第三方供应商进行沟通，对设计方案进行审核的能力;
### 要求
1. 5年软件研发/解决方案设计工作经验(金融领域经验加分)；
2. Java基础扎实，熟悉高级特性和类库、多线程编程以及常见框架(SpringBoot等)；
3. 具备基本系统架构能力，熟悉缓存、高可用等主流技术；
5. 持续保持技术激情，善于快速学习，注重代码质量，有良好的软件工程知识和编码规范意识；

