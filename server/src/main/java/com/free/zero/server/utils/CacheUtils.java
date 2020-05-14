package com.free.zero.server.utils;

import com.free.zero.server.mapper.OrderMapper;
import com.free.zero.server.pojo.OrderEntity;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by pijiang on 2020/5/13.
 * guava缓存工具类
 */
@Component
public class CacheUtils {

    //public公开
    public static OrderEntity getOrderCache(String OrderNo) {
        try {
            return CachesBuilder.orderCache.get(OrderNo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("load orderCache has error, " + e.getMessage());
        }
    }

    private static class CachesBuilder {
        private static LoadingCache<String, OrderEntity> orderCache = CacheBuilder.newBuilder()
                            .maximumSize(100)
                            .expireAfterAccess(1*60, TimeUnit.SECONDS)
                            .build(new CacheLoader<String, OrderEntity>() {

                                @Override
                                public OrderEntity load(String s) throws Exception {
                                    OrderMapper orderMapper = SpringUtils.getBean("orderMapper");
                                    OrderEntity rs = orderMapper.selectOne(new OrderEntity().setOrderNo(s));
                                    return ObjectUtils.isEmpty(rs) ? new OrderEntity() : rs;
                                }
                            });
    }

}