package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.mapper.CustomerMapper;
import com.zhongsheng.education.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public Integer addSignUp(Customer customer) {
        return customerMapper.addSignUp(customer);
    }

    public List<Customer> selectSignUp() {
        return customerMapper.selectSignUp();
    }

    ;

    public Integer updateSignUp(Integer id, String remark) {
        return customerMapper.updateSignUp(id, remark);

    }

    public Integer updateStatus(Integer id) {
        return customerMapper.updateStatus(id);
    }

    public List<JuniorCollege> selectJuniorCollege() {

        return customerMapper.selectJuniorCollege();
    }

    ;

    public List<RegularCollege> selectRegularCollegeId(Integer junior) {
        return customerMapper.selectRegularCollegeId(junior);
    }

    ;

    public Integer UpdateMajor(JuniorCollege juniorCollege) {
        return customerMapper.UpdateMajor(juniorCollege);
    }

    ;

    public Integer addMajor(String name) {
        return customerMapper.addMajor(name);
    }

    ;

    public RegularCollege selectSchool(Integer id) {
        return customerMapper.selectSchool(id);
    }

    ;

    public Integer setSchool(Integer schoolid, String c, Integer id) {
        StringBuffer sr = new StringBuffer();
        if (c != null && !c.equals("")) {
            String[] s = c.split(",");
            boolean a = true;
            for (int i = 0; i < s.length; i++) {
                if (schoolid.toString().equals(s[i])) {
                    a = false;
                }
            }
            if (a) {
                sr.append(c);
                sr.append(",");
                sr.append(schoolid);
            }
        } else {
            sr.append(schoolid);
        }

        String qwe = sr.toString();
        return customerMapper.setSchool(qwe, id);
    }

    public List<subject> selectSubject() {
        return customerMapper.selectSubject();
    }

    ;

    public Integer addSubject(String subjectname) {
        return customerMapper.addSubject(subjectname);
    }

    ;

    public Integer UpdateSubject(subject subject) {
        return customerMapper.UpdateSubject(subject);
    }

    ;

    public Integer deleteSubject(Integer sid) {
        return customerMapper.deleteSubject(sid);
    }

    ;

    public List<School> allSchool(String id) {
        if (id != null && !"".equals(id)) {
            return customerMapper.allSchool(id);
        } else {
            List<School> l = new ArrayList<>();
            return l;
        }

    }

    ;

    public List<YearData> selectYearDataId(Integer sid) {
        return customerMapper.selectYearDataId(sid);
    }

    ;

    public Integer addYearData(YearData yearData) {
        return customerMapper.addYearData(yearData);
    }

    ;

    public Integer deleteYearData(Integer id) {
        return customerMapper.deleteYearData(id);
    }

    ;

    public Integer deleteSchool(Integer id) {
        return customerMapper.deleteSchool(id);
    }

    ;

    public Integer deleteMajor(Integer id) {
        return customerMapper.deleteMajor(id);
    }

    ;

    public Integer deleteRegular(Integer id) {
        return customerMapper.deleteRegular(id);
    }

    ;

    public List<School> allSchoolInfo() {
        return customerMapper.allSchoolInfo();
    }

    ;

    public Integer addSchoolInfo(School school) {
        return customerMapper.addSchoolInfo(school);
    }

    ;

    public Integer addRegular(RegularCollege regular) {
        return customerMapper.addRegular(regular);
    }

    ;

    public List<RegularCollege> xSelectJunior(Integer sid, String name) {
        return customerMapper.xSelectJunior(sid, name);
    }

    ;

    public List<subject> xSelectJunior1(String name) {
        return customerMapper.xSelectJunior1(name);
    }

    ;

    public List<School> xSelectSchool(String id, String status) {
        if (status.isEmpty() && status == "" && status.equals("")) {
            return customerMapper.allSchool(id);
        } else if (status.equals("0")) {
            return customerMapper.allSchool(id);
        } else {
            return customerMapper.xSelectSchool(id, status);
        }
    }

    ;

    public ArrayList<String> selectYearDataId1(Integer id,Integer year) {
        DeskMonth1 d = new DeskMonth1();
        ArrayList<YearData> yearData = customerMapper.selectYearDataId1(id,year);
        for (int i = 0; i < yearData.size(); i++) {
            d.setOne(String.valueOf(yearData.get(i).getData1()));
            d.setTwo(String.valueOf(yearData.get(i).getData2()));
            d.setThree(String.valueOf(yearData.get(i).getData3()));
        }
        ArrayList<String> arr = new ArrayList<>();
            arr.add(0,d.getOne());
            arr.add(1,d.getTwo());
            arr.add(2,d.getThree());
        return arr;
    }

    ;

   public Integer  UpdateSchoolInfo(School school){
       return customerMapper.UpdateSchoolInfo(school);
   };

    // 求两个数组的差集
    public static String[] minus(String[] arr1, String[] arr2) {
        LinkedList<String> list = new LinkedList<String>();
        LinkedList<String> history = new LinkedList<String>();
        String[] longerArr = arr1;
        String[] shorterArr = arr2;
        // 找出较长的数组来减较短的数组
        if (arr1.length > arr2.length) {
            longerArr = arr2;
            shorterArr = arr1;
        }
        for (String str : longerArr) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        for (String str : shorterArr) {
            if (list.contains(str)) {
                history.add(str);
                list.remove(str);
            } else {
                if (!history.contains(str)) {
                    list.add(str);
                }
            }
        }

        String[] result = {};
        return list.toArray(result);
    }
  public Integer deleteSchoolInfo(Integer rid,String sid,Integer id){
      String[]  s=sid.split(",");
      StringBuffer sr= new StringBuffer();
      String  id1=String.valueOf(id);
      String[]  s1=id1.split(",");
      String [] ids = minus(s,s1);
      for (int i = 0; i <ids.length; i++) {
          System.out.println("之后============================="+ids[i]);
          sr.append(ids[i]);
          if (i<ids.length-1){
              sr.append(",");
          }
      }
      String qwe=  sr.toString();
      return customerMapper.setSchool(qwe,rid);
  };
}
