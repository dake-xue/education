package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.mapper.StudentMapper;
import com.zhongsheng.education.service.FamilyService;
import com.zhongsheng.education.service.SchoolService;
import com.zhongsheng.education.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private SchoolService schoolService;

    @Override
    public User selectWho(Integer username, String password) {

        return studentMapper.selectWho(username, password);
    }


    /**
     * @创建人 xueke
     * @创建时间 2020/9/22
     * @描述 根据id查询学生
    */
    @Override
    public Student selectStudent(Integer uid) {
        return studentMapper.selectStudent(uid);
    }


    @Override
    public List<Student> selectAllStudent(String keyword,Integer modules) {
        return studentMapper.selectAllStudent(keyword,modules);
    }


    @Override
    public List<Student> allStudent() {
        return studentMapper.allStudent();
    }

    @Override
    public int addStudentInfo(Student student) {
        familyService.addFamilyInfo(student.getFamilyInfo());
        schoolService.addSchoolInfo(student.getSchoolInfo());
        return studentMapper.addStudentInfo(student);
    }


    public Integer addScore(Integer sid, Integer scope) {
        return studentMapper.addScore(sid, scope);
    }


}
