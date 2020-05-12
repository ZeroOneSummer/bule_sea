package com.free.zero.server.mapper;

import com.free.zero.server.pojo.OrderEntity;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by pijiang on 2020/5/2.
 */
@SuppressWarnings("all")
public interface OrderMapper extends Mapper<OrderEntity> {

    //自定义
    List<OrderEntity> getOrders();

    //脚本方式
    @Select("<script>" +
            "        select\n" +
            "            *\n" +
            "        from\n" +
            "            `order`\n" +
            "        <where>\n" +
            "            <if test=\"id != null and id != 0\">\n" +
            "                and id = #{id}\n" +
            "            </if>\n" +
            "            <if test=\"orderNo != null and orderNo != ''\">\n" +
            "                and order_no = #{orderNo}\n" +
            "            </if>\n" +
            "            <if test=\"goodsName != null and goodsName != ''\">\n" +
            "                and goods_name = #{goodsName}\n" +
            "            </if>\n" +
            "        </where>" +
            "</script>")
    List<OrderEntity> getOrdersForScript(OrderEntity entity);
}
