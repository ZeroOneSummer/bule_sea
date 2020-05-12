package com.free.zero.server;

import com.alibaba.fastjson.JSON;
import com.free.zero.server.pojo.OrderEntity;
import com.free.zero.server.server.OrderService;
import com.free.zero.server.utils.RedisUtils;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RedisTests {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private OrderService orderService;

    private final static Gson gson = new Gson();
    private final static String TEST_KEY = "zero_redis_test_key";
    private final static int EXPIRE_TIME = 60;

    @Test
    void optAddTest() {
        //Insert
        PageInfo<OrderEntity> ps = orderService.getOrdersForScript(1, 50, new OrderEntity().setOrderNo("20200503072423"));
        OrderEntity entity = ps.getList().stream().findFirst().orElse(null);
        log.info(JSON.toJSONString(ps.getList().get(0)));
        redisUtils.set(TEST_KEY, entity, EXPIRE_TIME);
    }

    @Test
    void optSelectTest() {
        //Select
        OrderEntity entity = redisUtils.get(TEST_KEY, OrderEntity.class);
        log.info(gson.toJson(entity));
    }

}
