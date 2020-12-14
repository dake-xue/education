package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.*;

import java.util.ArrayList;
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
    Integer setSchool(Integer schoolid, String c, Integer id);
    List<subject> selectSubject();
    Integer addSubject(String subjectname);
    Integer UpdateSubject(subject subject);
    Integer deleteSubject(Integer sid);
    List<YearData>  selectYearDataId(Integer sid);
    Integer  addYearData(YearData yearData);
    Integer  deleteYearData(Integer id);
    Integer  deleteSchool(Integer id);
    Integer  deleteMajor(Integer id);
    Integer  deleteRegular(Integer id);
    List<School>  allSchoolInfo();
    Integer addSchoolInfo(School school);
    Integer  addRegular(RegularCollege regular);
    List<School>  allSchool(String id);
    List<RegularCollege>  xSelectJunior(Integer sid,String name);
    List<subject>  xSelectJunior1(String name);
    List<School>  xSelectSchool(String id,String status);
    ArrayList<String> selectYearDataId1(Integer id,Integer year);
    Integer  UpdateSchoolInfo(School school);
    Integer deleteSchoolInfo(Integer rid,String sid,Integer id);
}
