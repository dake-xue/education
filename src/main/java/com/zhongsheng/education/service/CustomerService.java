package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.*;

import java.util.List;

public interface CustomerService {
    Integer addSignUp(Customer customer);
    List<Customer> selectSignUp();
     Integer updateSignUp(Integer id, String remark);
     Integer updateStatus(Integer id);
    List<JuniorCollege> selectJuniorCollege();
    List<RegularCollege> selectRegularCollegeId(Integer junior);
    Integer UpdateMajor(JuniorCollege juniorCollege);
    Integer addMajor(String name);
    RegularCollege selectSchool(Integer id);
    String  setSchool(Integer schoolid,String c,Integer id);
    List<subject> selectSubject();
    Integer addSubject(String subjectname);
    Integer UpdateSubject(subject subject);
    Integer deleteSubject(Integer sid);
    List<School>  allSchool(Integer id);

}
