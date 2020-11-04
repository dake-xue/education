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
    };

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

    public Statistics people(Bill bill){
        return billMapper.people(bill);
    }

/*    public Integer money(Bill bill){
        return billMapper.money(bill);
    }*/

   public List<Student> selectStudentInfo(Bill bill){
        return billMapper.selectStudentInfo(bill);
   };


}
