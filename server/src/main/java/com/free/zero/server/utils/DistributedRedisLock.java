package com.free.zero.server.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式Redis锁
 */
@Slf4j
@Component
public class DistributedRedisLock {

    @Autowired
    private RedissonClient redissonClient;

    // 加锁
    public Boolean lock(String lockName) {
        try {
            if (redissonClient == null) {
                log.info("DistributedRedisLock redissonClient is null");
                return false;
            }

            RLock lock = redissonClient.getLock(lockName);
            // 锁10秒后自动释放，防止死锁
            lock.lock(10, TimeUnit.SECONDS);

            log.info("Thread [{}] DistributedRedisLock lock [{}] success", Thread.currentThread().getName(), lockName);
            // 加锁成功
            return true;
        } catch (Exception e) {
            log.error("DistributedRedisLock lock [{}] Exception:", lockName, e);
            return false;
        }
    }

    // 释放锁
    public Boolean unlock(String lockName) {
        try {
            if (redissonClient == null) {
                log.info("DistributedRedisLock redissonClient is null");
                return false;
            }
            RLock lock = redissonClient.getLock(lockName);
            lock.unlock();
            log.info("Thread [{}] DistributedRedisLock unlock [{}] success", Thread.currentThread().getName(), lockName);
            // 释放锁成功
            return true;
        } catch (Exception e) {
            log.error("DistributedRedisLock unlock [{}] Exception:", lockName, e);
            return false;
        }
    }

    //是否持有锁
    public Boolean isLocked(String lockName) {
            RLock lock = redissonClient.getLock(lockName);
            return lock.isLocked();
    }

}