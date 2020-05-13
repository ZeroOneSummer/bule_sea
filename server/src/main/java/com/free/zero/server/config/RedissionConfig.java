package com.free.zero.server.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Redission配置类
 */
@Slf4j
@Configuration
@Component
@EnableCaching
public class RedissionConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean(destroyMethod="shutdown")
    public RedissonClient redissonClient() {
        String url = null;
        try {
            Config config = new Config();
            url = "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort();
            config.useSingleServer().setAddress(url).setDatabase(redisProperties.getDatabase());   //无密码，不需要添加参数，否则异常
            return Redisson.create(config);
        } catch (Exception e) {
            log.error("RedissonClient init redis url:[{}], Exception:", url, e);
            return null;
        }
    }

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
        // 创建一个名称为"redisCacheMap"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
        config.put("redisCacheMap", new CacheConfig(24*60*1000L, 12*60*1000L));
        return new RedissonSpringCacheManager(redissonClient, config);
    }

}