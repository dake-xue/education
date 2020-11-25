package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Insert("insert into signup() values(#{paymentAmount},#{snum},#{image},#{intotime})")
    public Integer addSignUp();

    @Select("select * from signup")
    public List<Customer> selectSignUp();

}
