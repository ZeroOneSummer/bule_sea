package com.free.zero.server.controller;

import com.free.zero.server.pojo.OrderEntity;
import com.free.zero.server.server.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by pijiang on 2020/5/2.
 */
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("query/{id}")
    @ResponseBody
    public OrderEntity getOrders(@PathVariable(name = "id") int id){
        return orderService.getOrder(new OrderEntity().setId(id));
    }

    @GetMapping("/add")
    @ResponseBody
    public String addOrder(){
        int ret = orderService.insertOrder(new OrderEntity()
                .setStatus(0)
                .setOrderNo("20200503"+String.format("%06d", new Random().nextInt(100000)))
                .setGoodsName("芒果欧蕾-大杯"));
        Assert.state(ret == 1, "添加失败");
        return "OK";
    }
}
