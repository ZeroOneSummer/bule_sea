package com.free.zero.server.server.impl;

import com.free.zero.server.dao.MixMapper;
import com.free.zero.server.pojo.OrderEntity;
import com.free.zero.server.server.MixServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pijiang on 2020/5/2.
 */
@SuppressWarnings("all")
@Service
public class MixServerImpl implements MixServer {

    @Autowired
    private MixMapper mixMapper;

    @Override
    public List<OrderEntity> queryOrderList(OrderEntity order) {
        return mixMapper.queryOrderList(order);
    }

    @Override
    public int addOrder(OrderEntity order) {
        return mixMapper.addOrder(order);
    }
}
