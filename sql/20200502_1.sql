
CREATE TABLE `order` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_no` varchar(20) DEFAULT NULL COMMENT '订单号',
  `goods_name` varchar(50) DEFAULT NULL COMMENT '商品名',
	`status` int(1) default 0 comment '状态：-2-退单完成，-1-退单中，1待出售，2-已下单，3-已完成',
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';