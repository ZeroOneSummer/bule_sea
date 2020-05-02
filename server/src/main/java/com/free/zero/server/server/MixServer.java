package com.free.zero.server.server;

import com.free.zero.server.pojo.OrderEntity;

import java.util.List;

/**
 * Created by pijiang on 2020/5/2.
 */
public interface MixServer {
    List<OrderEntity> queryOrderList(OrderEntity order);
    int addOrder(OrderEntity order);
}
