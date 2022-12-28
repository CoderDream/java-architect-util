# 项目简介

根据链接自动生成视频
1、根据链接生成URL；
2、根据链接爬虫介绍；
3、根据链接爬虫图片；
4、生成的视频存放在minio服务器中；
5、定时任务；
6、链接存放在数据库中





### Chrome



```
Exception in thread "main" java.lang.NoSuchMethodError: com.google.common.util.concurrent.SimpleTimeLimiter.create

Exception in thread "main" java.lang.NoSuchMethodError: com.google.common.util.concurrent.SimpleTimeLimiter.create(Ljava/util/concurrent/ExecutorService;)Lcom/google/common/util/concurrent/SimpleTimeLimiter;
	at org.openqa.selenium.net.UrlChecker.<init>(UrlChecker.java:62)
	at org.openqa.selenium.remote.service.DriverService.waitUntilAvailable(DriverService.java:197)
	at org.openqa.selenium.remote.service.DriverService.start(DriverService.java:188)
	at org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:79)
	at org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)
	at org.openqa.selenium.remote.RemoteWebDriver.startSession(RemoteWebDriver.java:213)
	at org.openqa.selenium.remote.RemoteWebDriver.<init>(RemoteWebDriver.java:131)
	at org.openqa.selenium.chrome.ChromeDriver.<init>(ChromeDriver.java:181)
	at org.openqa.selenium.chrome.ChromeDriver.<init>(ChromeDriver.java:168)
	at org.openqa.selenium.chrome.ChromeDriver.<init>(ChromeDriver.java:123)
	at com.example.demo.selenium.TestChromeDriver.testHelloWord(TestChromeDriver.java:44)
	at com.example.demo.selenium.TestChromeDriver.main(TestChromeDriver.java:18)

Process finished with exit code 1

```



使用的是Guava 20的包
