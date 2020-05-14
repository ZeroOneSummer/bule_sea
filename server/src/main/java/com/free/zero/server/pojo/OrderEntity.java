package com.free.zero.server.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by pijiang on 2020/5/2.
 */
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "`order`")
public class OrderEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;       // "JDBC"-返回主键id，必须是包装类
    private String orderNo;
    private String goodsName;
    private Integer status;
    private Integer stock;
}
