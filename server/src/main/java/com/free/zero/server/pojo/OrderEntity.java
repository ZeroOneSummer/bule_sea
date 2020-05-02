package com.free.zero.server.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by pijiang on 2020/5/2.
 */
@Setter
@Getter
@Accessors(chain = true)
public class OrderEntity extends BaseEntity{
    private int id;
    private String orderNo;
    private String goodsName;
    private int status;
}
