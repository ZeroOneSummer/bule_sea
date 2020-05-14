package com.free.zero.server.server;

import com.free.zero.server.pojo.OrderEntity;
import com.github.pagehelper.PageInfo;

/**
 * Created by pijiang on 2020/5/2.
 */
public interface OrderService  {
    PageInfo<OrderEntity> getOrders(int index, int size);
    PageInfo<OrderEntity> getOrdersForScript(int index, int size, OrderEntity entity);
    OrderEntity getOrdersByRedis(String OrderNo);
    OrderEntity getOrdersByGuava(String OrderNo);
    PageInfo<OrderEntity> getAll();
    OrderEntity getOrderById(int id);
    OrderEntity getOrder(OrderEntity entity);
    int insertOrder(OrderEntity entity);
}
