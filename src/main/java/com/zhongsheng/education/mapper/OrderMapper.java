package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {

    @Insert("insert into orders(order_number,goods_name,price,create_time,sNum) VALUES(#{order_number},#{goods_name},#{price},#{create_time},#{sNum})")
    Integer addOrder(Order order);

    @Select("select * from orders where order_number = #{order_number}")
    Order searchByOrderNum (String order_number);

    @Update("UPDATE orders set status = #{status} where order_number = #{order_number}")
    Integer updateStatus(Order order);
}

