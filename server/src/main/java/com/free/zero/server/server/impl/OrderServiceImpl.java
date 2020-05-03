package com.free.zero.server.server.impl;

import com.free.zero.server.mapper.OrderMapper;
import com.free.zero.server.pojo.OrderEntity;
import com.free.zero.server.server.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pijiang on 2020/5/2.
 */
@SuppressWarnings("all")
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<OrderEntity> getOrders(int index, int size) {
        PageHelper.startPage(index, size);
        return PageInfo.of(orderMapper.getOrders());
    }

    @Override
    public PageInfo<OrderEntity> getAll() {
        return PageInfo.of(orderMapper.selectAll());
    }

    @Override
    public OrderEntity getOrderById(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderEntity getOrder(OrderEntity entity) {
        return orderMapper.selectOne(entity);
    }

    @Override
    public int insertOrder(OrderEntity entity) {
        return orderMapper.insert(entity);
    }
}
