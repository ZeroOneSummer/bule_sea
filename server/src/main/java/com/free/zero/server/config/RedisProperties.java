package com.free.zero.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Redis配置文件的参数
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Validated
public class RedisProperties {
    private int database;
    private String host;
    private int port;
    private String password;
}