package com.free.zero.server.mapper;

import com.free.zero.server.pojo.OrderEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by pijiang on 2020/5/2.
 */
@SuppressWarnings("all")
public interface OrderMapper extends Mapper<OrderEntity> {

    //自定义
    List<OrderEntity> getOrders();
}
