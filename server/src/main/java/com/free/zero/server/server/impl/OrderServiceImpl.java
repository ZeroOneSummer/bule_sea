package com.free.zero.server.server.impl;

import com.free.zero.server.mapper.OrderMapper;
import com.free.zero.server.pojo.OrderEntity;
import com.free.zero.server.server.OrderService;
import com.free.zero.server.utils.CacheUtils;
import com.free.zero.server.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pijiang on 2020/5/2.
 */
@SuppressWarnings("all")
@Slf4j
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageInfo<OrderEntity> getOrders(int index, int size) {
        PageHelper.startPage(index, size);
        return PageInfo.of(orderMapper.getOrders());
    }

    @Override
    public PageInfo<OrderEntity> getOrdersForScript(int index, int size, OrderEntity entity) {
        PageHelper.startPage(index, size);
        return PageInfo.of(orderMapper.getOrdersForScript(entity));
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

    @Override
    public OrderEntity getOrdersByRedis(String OrderNo) {
        String order = redisUtils.get(OrderNo);
        if (StringUtils.isBlank(order)) {
            OrderEntity entity = orderMapper.selectOne(new OrderEntity().setOrderNo(OrderNo));
            if (entity != null) redisUtils.set(OrderNo, entity);
            return entity;
        }
        log.info(OrderNo + "命中缓存");
        return new Gson().fromJson(order, OrderEntity.class);
    }

    @Override
    public OrderEntity getOrdersByGuava(String OrderNo) {
        return CacheUtils.getOrderCache(OrderNo);
    }

}
