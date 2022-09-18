package com.company.microserviceuser.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Thread pool configuration.
 * 线程池参数获取，
 * 通过注解@ConfigurationProperties获取配置文件中的数据，
 * 由于参数较多，因此代替@Value注解获取配置参数
 * @author xindaqi
 * @since 2020-10-17
 */

@ConfigurationProperties(prefix = "task.pool")
public class ThreadPoolConfig {
    
    private Integer corePoolSize;

    private Integer maxPoolSize;

    private Integer keepAliveSeconds;

    private Integer queueCapacity;

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }
    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setKeepAliveSeconds(Integer keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }
    public Integer getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
    public Integer getQueueCapacity() {
        return queueCapacity;
    }

}