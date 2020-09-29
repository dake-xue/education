package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Bill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BillMapper {

    @Insert("insert into bill(paymentAmount,snum,image) values(#{paymentAmount},#{snum},#{image})")
    public Integer addBillInfo(Bill bill);

    @Select("select * from bill where snum = #{snum}")
    public List<Bill> selectBill(String snum);


}
