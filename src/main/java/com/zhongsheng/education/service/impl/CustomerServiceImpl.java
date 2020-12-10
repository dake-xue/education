package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.mapper.CustomerMapper;
import com.zhongsheng.education.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (id!=null && !"".equals(id)){
            return customerMapper.allSchool(id);
        }else {
            List<School> l=new ArrayList<>();
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

  public   List<School>  allSchoolInfo(){
      return customerMapper.allSchoolInfo();
  };
 public    Integer addSchoolInfo(School school){
     return customerMapper.addSchoolInfo(school);
 };

 public Integer  addRegular(RegularCollege regular){
     return customerMapper.addRegular(regular);
 };


}
