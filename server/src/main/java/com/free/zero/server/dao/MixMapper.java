package com.free.zero.server.dao;

import com.free.zero.server.pojo.OrderEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by pijiang on 2020/5/2.
 */
public interface MixMapper {

    @Select("<script>" +
                "<select id=\"queryOrderList\" resultType=\"OrderEntity\">" +
                    "select \n" +
                    "  * \n" +
                    "from \n" +
                    "  `order`\n" +
                    "<where>\n" +
                    "   <if test=\"id != 0\">\n" +
                    "       and id = #{id, jdbcType=INTEGER}\n" +
                    "   </if>\n" +
                    "   <if test=\"orderNo != null and orderNo != ''\">\n" +
                    "       and order_no = #{orderNo, jdbcType=VARCHAR}\n" +
                    "   </if>\n" +
                    "   <if test=\"status != 0\">\n" +
                    "       and status = #{status, jdbcType=INTEGER}\n" +
                    "   </if>\n" +
                    "</where>" +
                "</select>" +
            "</script>")
    List<OrderEntity> queryOrderList(OrderEntity order);

    int addOrder(OrderEntity order);
}
