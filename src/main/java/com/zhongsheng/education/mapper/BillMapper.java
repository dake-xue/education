package com.zhongsheng.education.mapper;

import com.zhongsheng.education.dao.BillDao;
import com.zhongsheng.education.entiy.*;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BillMapper {

    @Insert("insert into bill(paymentAmount,snum,image,intotime,area,campusid,schoolid,billnumber) values(#{paymentAmount},#{snum},#{image},#{intotime},#{area},#{campusid},#{schoolid},#{billnumber})")
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

    /**
     * @创建人 xueke
     * @创建时间 2020/11/25
     * @描述 修改时间
    */
    @Update("update bill set intotime=#{intotime} where paymentAmount=#{paymentAmount} and snum=#{snum}")
    Integer updateIntoTimeByMoneyAndSnum(Bill bill);
}
