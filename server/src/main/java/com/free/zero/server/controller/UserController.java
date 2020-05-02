package com.free.zero.server.controller;

import com.free.zero.server.pojo.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Created by pijiang on 2020/5/2.
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${aaa:default}")
    private String aaa;

    @Value("${bbb:default}")
    private String bbb;

    // http://localhost:8080/user/2
    @GetMapping("/{id}")
    @ResponseBody
    public String getUserOrders(@PathVariable(name = "id") int userId){
        String userInfo = "用户"+userId+"已购买的商品：";

        OrderEntity order = new OrderEntity()
                            .setOrderNo("202005020001")
                            .setGoodsName("友谊729-三星-横拍");

        return userInfo.concat(order.toString());
    }

    //测试跳转
    @GetMapping("/main")
    public String toPage(){
        log.info("aaa: " + aaa + ", bbb: " + bbb);
        return "common/main";
    }
}
