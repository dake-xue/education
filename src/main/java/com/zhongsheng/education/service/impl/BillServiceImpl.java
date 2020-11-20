package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.mapper.BillMapper;
import com.zhongsheng.education.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;

    @Override
    public Integer addBillInfo(Bill bill) { return billMapper.addBillInfo(bill);
    }
    @Override
    //查询总金额
    public String selectJiaoFeiJinE(String snum){
        List<Bill> bills = billMapper.selectBill(snum);
        BigDecimal bigDecimal = new BigDecimal("0");
        for (int i = 0; i < bills.size(); i++) {
            bigDecimal.add(new BigDecimal(bills.get(i).getPaymentAmount()));
        }
        return bigDecimal.toString();
    };

    @Override
    public List<Bill> selectBill(String snum){
        return billMapper.selectBill(snum);
    }
    @Override
    public ArrayList<String> peopleCounts(Bill bill){
        DeskMonth dm=new DeskMonth();
        ArrayList<Desk> d = billMapper.peopleCounts(bill);

        for(int i=0;i<d.size();i++){
            if(d.get(i).getMonth().equals("01")){
                dm.setOne(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("02")){
                dm.setTwo(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("03")){
                dm.setThree(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("04")){
                dm.setFour(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("05")){
                dm.setFive(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("06")){
                dm.setSix(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("07")){
                dm.setSeven(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("08")){
                dm.setEight(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("09")){
                dm.setNine(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("10")){
                dm.setTen(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("11")){
                dm.setEleven(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("12")){
                dm.setTwelve(d.get(i).getVal());
            }
        }
        ArrayList<String> arr = new ArrayList<>();
        arr.add(0,dm.getOne());
        arr.add(1,dm.getTwo());
        arr.add(2,dm.getThree());
        arr.add(3,dm.getFour());
        arr.add(4,dm.getFive());
        arr.add(5,dm.getSix());
        arr.add(6,dm.getSeven());
        arr.add(7,dm.getEight());
        arr.add(8,dm.getNine());
        arr.add(9,dm.getTen());
        arr.add(10,dm.getEleven());
        arr.add(11,dm.getTwelve());
        return arr;
    }
    @Override
    public  ArrayList<String> moneyCounts(Bill bill){
        DeskMonth dm=new DeskMonth();
        ArrayList<Desk> d = billMapper.moneyCounts(bill);

        for(int i=0;i<d.size();i++){
            if(d.get(i).getMonth().equals("01")){
                dm.setOne(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("02")){
                dm.setTwo(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("03")){
                dm.setThree(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("04")){
                dm.setFour(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("05")){
                dm.setFive(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("06")){
                dm.setSix(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("07")){
                dm.setSeven(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("08")){
                dm.setEight(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("09")){
                dm.setNine(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("10")){
                dm.setTen(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("11")){
                dm.setEleven(d.get(i).getVal());
            }
            if(d.get(i).getMonth().equals("12")){
                dm.setTwelve(d.get(i).getVal());
            }
        }
        ArrayList<String> arr = new ArrayList<>();
        arr.add(0,dm.getOne());
        arr.add(1,dm.getTwo());
        arr.add(2,dm.getThree());
        arr.add(3,dm.getFour());
        arr.add(4,dm.getFive());
        arr.add(5,dm.getSix());
        arr.add(6,dm.getSeven());
        arr.add(7,dm.getEight());
        arr.add(8,dm.getNine());
        arr.add(9,dm.getTen());
        arr.add(10,dm.getEleven());
        arr.add(11,dm.getTwelve());
        return arr;
    };
    @Override
    public Statistics people(Bill bill){
        return billMapper.people(bill);
    }

    @Override
    public String money(Bill bill){
        String money = billMapper.money(bill);
        if(money!=null){
            return billMapper.money(bill);
        }
        return "0.00";
    }
    /**
     * @创建人 xueke
     * @创建时间 2020/11/4
     * @描述
    */
    @Override
    public List<Student> selectStudentInfo(Bill bill){
        List<Student> studentList =  billMapper.selectStudentInfo(bill);
        for (Student stu : studentList) {
            //学费
            BigDecimal money = new BigDecimal(stu.getMoney());
            //缴费金额
            BigDecimal jfje = new BigDecimal(stu.getJiaofeijine());
            stu.setJiaofeijine(jfje.toString());
            //待补款
            BigDecimal wjje = money.subtract(jfje);
            stu.setWeijiaokuan(wjje.toString());
        }
        return studentList;
   }


}
