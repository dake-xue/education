package com.zhongsheng.education.mapper;

import com.zhongsheng.education.dao.BillDao;
import com.zhongsheng.education.entiy.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BillMapper {

    @Insert("insert into bill(paymentAmount,snum,image,intotime,area,campusid) values(#{paymentAmount},#{snum},#{image},#{intotime},#{area},#{campusid})")
    public Integer addBillInfo(Bill bill);

    @Select("select * from bill where snum = #{snum}")
    public List<Bill> selectBill(String snum);

    @SelectProvider(type = BillDao.class,method = "peopleCounts")
    public ArrayList<Desk> peopleCounts(Bill bill);

    @SelectProvider(type = BillDao.class, method = "moneyCounts")
    public  ArrayList<Desk> moneyCounts(Bill bill);

    @SelectProvider(type = BillDao.class, method = "people")
    public Statistics people(Bill bill);

    @SelectProvider(type = BillDao.class, method = "money")
    public String money(Bill bill);

    @SelectProvider(type = BillDao.class, method = "selectStudentInfo")
    public  List<Student> selectStudentInfo(Bill bill);
    @Select("select * from orders where snum=#{snum}")
    public  List<Order> selectJiaoFeiJinE(String snum);
}
