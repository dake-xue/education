package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {

    @Insert("insert into orders(order_number,goods_name,price,create_time,sNum,areaid) VALUES(#{order_number},#{goods_name},#{price},#{create_time},#{sNum},#{area})")
    Integer addOrder(Order order);

    @Select("select * from orders where order_number = #{order_number}")
    Order searchByOrderNum (String order_number);

    @Select("select * from orders where snum = #{snum} and status = 0")
    Order searchOrderBySnum (String snum);

    @Update("UPDATE orders set status = #{status} where order_number = #{order_number}")
    Integer updateStatus(Order order);
}

