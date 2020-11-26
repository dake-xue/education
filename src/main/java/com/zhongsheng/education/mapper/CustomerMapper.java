package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Insert("insert into signup(name,phone,qq,schoolname,major,period) values(#{name},#{phone},#{qq},#{schoolname},#{major},#{period})")
    public Integer addSignUp(Customer customer);

    @Select("select * from signup")
    public List<Customer> selectSignUp();

}
