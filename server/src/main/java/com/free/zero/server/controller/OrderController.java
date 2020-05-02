package com.free.zero.server.controller;

import com.free.zero.server.pojo.OrderEntity;
import com.free.zero.server.server.MixServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by pijiang on 2020/5/2.
 */
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private MixServer mixServer;

    @GetMapping("query/{id}")
    @ResponseBody
    public List<OrderEntity> getOrders(@PathVariable(name = "id") int id){
        return mixServer.queryOrderList(new OrderEntity().setId(id));
    }

    @GetMapping("/add")
    @ResponseBody
    public String addOrder(){
        OrderEntity orderEntity = new OrderEntity()
                                .setOrderNo("YG" + new Date().getTime() + new Random().nextInt(3))
                                .setGoodsName("友谊729-" + new Random().nextInt(3));
        int ret = mixServer.addOrder(orderEntity);
        Assert.state(ret == 1, "添加失败");
        return "OK";
    }
}
