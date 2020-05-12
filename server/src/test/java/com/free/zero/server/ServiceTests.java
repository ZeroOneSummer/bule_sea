package com.free.zero.server;

import com.alibaba.fastjson.JSON;
import com.free.zero.server.pojo.OrderEntity;
import com.free.zero.server.server.OrderService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@Slf4j
@SpringBootTest
class ServiceTests {

    @Autowired
    private OrderService orderService;

    @Test
    void mixOrderTest() {
        //自定义查询
        PageInfo<OrderEntity> p1 = orderService.getOrders(1, 50);
        log.info(JSON.toJSONString(p1.getList()));

        //自定义script查询
        PageInfo<OrderEntity> ps = orderService.getOrdersForScript(1, 50, new OrderEntity().setOrderNo("20200503072423"));
        log.info(JSON.toJSONString(ps.getList()));

        //Select
        PageInfo<OrderEntity> p2 = orderService.getAll();
        log.info(JSON.toJSONString(p2.getList()));

        OrderEntity o1 = orderService.getOrderById(2);
        log.info(JSON.toJSONString(o1));

        OrderEntity o2 = orderService.getOrder(new OrderEntity().setId(1));
        log.info(JSON.toJSONString(o2));

        //Insert
        int ret = orderService.insertOrder(new OrderEntity()
                                            .setStatus(0)
                                            .setOrderNo("20200503"+String.format("%06d", new Random().nextInt(100000)))
                                            .setGoodsName("芒果欧蕾-大杯"));
        log.info(String.valueOf(ret));

    }

}
