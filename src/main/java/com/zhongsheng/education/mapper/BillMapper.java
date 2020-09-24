package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Bill;
import com.zhongsheng.education.entiy.FamilyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BillMapper {

    @Insert("insert into bill(paymentAmount,bsid,image) values(#{paymentAmount},#{bsid},#{image})")
    public int addBillInfo(Bill bill);

    @Select("select * from familyinfo where sfid=#{sid}")
    public FamilyInfo selectFamilyInfo(Integer sid);
}
