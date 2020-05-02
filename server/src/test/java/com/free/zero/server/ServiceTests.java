package com.free.zero.server;

import com.alibaba.fastjson.JSON;
import com.free.zero.server.pojo.OrderEntity;
import com.free.zero.server.server.MixServer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
class ServiceTests {

    @Autowired
    private MixServer mixServer;

    @Test
    void addOrder() {
        OrderEntity order = new OrderEntity()
                            .setOrderNo("YG" + new Date().getTime() + new Random().nextInt(3))
                            .setGoodsName("友谊729-" + new Random().nextInt(3));
        int ret = mixServer.addOrder(order);
        log.info(String.valueOf(ret));
    }

    @Test
    void getOrders() {
        List<OrderEntity> list = mixServer.queryOrderList(new OrderEntity());
        log.info(JSON.toJSONString(list));
    }

}
