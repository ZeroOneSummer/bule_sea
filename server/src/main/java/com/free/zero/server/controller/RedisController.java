package com.free.zero.server.controller;

import com.alibaba.fastjson.JSON;
import com.free.zero.server.pojo.OrderEntity;
import com.free.zero.server.server.OrderService;
import com.free.zero.server.utils.DistributedRedisLock;
import com.free.zero.server.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pijiang on 2020/5/2.
 * 使用jmeter压测
 */
@Slf4j
@RestController
@RequestMapping("/")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private DistributedRedisLock redisLock;

    @Autowired
    private OrderService orderService;

    private final static String ITEM_COUNT = "item:count";
    private final static String LOCK_KEY = "item:count:lock";
    private final static long EXPIRE = 2*60L;

    /**
     * 初始化商品数量
     * http://localhost:8088/setCount/1000
     */
    @RequestMapping("/setCount/{num}")
    public String setCount(@PathVariable("num") int value) {
        redisUtils.set(ITEM_COUNT, value + "", EXPIRE);
        return "success:"+getCount();
    }

    /**
     * 查询商品数量
     */
    @RequestMapping("/getCount")
    public String getCount() {
        return redisUtils.get(ITEM_COUNT);
    }

    /**
     * 模拟秒杀抢购
     * http://localhost:8088/ms
     */
    @RequestMapping("/ms")
    public String msLock() {
        String result = "fail";
        if (redisLock.lock(LOCK_KEY)) {
            try {
                int stock = Integer.parseInt(redisUtils.get(ITEM_COUNT));
                if (stock > 0) {
                    result = "success";
                    redisUtils.set(ITEM_COUNT, (stock - 1) + "", EXPIRE);
                } else {
                    log.info("很遗憾你来晚了，商品剩余："+getCount());
                }
            } finally {
                redisLock.unlock(LOCK_KEY);
            }
        } else {
            log.info("人数过多，请稍后再试...");
        }
        log.info(Thread.currentThread().getName() + ", result: " + result);
        return result + ": " + getCount();
    }

    /**
     * 模拟秒杀抢购
     * http://localhost:8088/nms
     */
    @RequestMapping("/nms")
    public String msNoLock() {
        String result = "fail";
        int stock = Integer.parseInt(redisUtils.get(ITEM_COUNT));
        if (stock > 0) {
            result = "success";
            redisUtils.set(ITEM_COUNT, (stock - 1) + "", EXPIRE);
            log.info("商品剩余："+getCount());
        } else {
            log.info("很遗憾你来晚了，商品已被抢购光了");
        }
        log.info(Thread.currentThread().getName() + ", result: " + result);
        return result + ": " + getCount();
    }

    /**
     * guava缓存
     * http://localhost:8088/order/guava
     */
    @RequestMapping("/order/guava")
    public OrderEntity optSelectTestByGuavaCache() {
        //GuavaCache
        OrderEntity ordersByGuava = orderService.getOrdersByGuava("redis-20200513");
        log.info(JSON.toJSONString(ordersByGuava));
        return ordersByGuava;
    }

}
