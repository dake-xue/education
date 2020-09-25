package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.mapper.StudentMapper;
import com.zhongsheng.education.service.BillService;
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
    @Autowired
    private BillService billService;



    /**
     * @创建人 xueke
     * @创建时间 2020/9/22
     * @描述 根据id查询学生
    */
    @Override
    public Student selectStudent(String snum) {
        //学生
        Student student = studentMapper.selectStudent(snum);
        //家庭和学校
        student.setFamilyInfo(familyService.selectFamilyInfo(student.getSid()));
        student.setSchoolInfo(schoolService.selectSchoolInfo(student.getSid()));
        //票据
        student.setBillList(billService.selectBill(student.getSid()));

        return student;
    }


    @Override
    public List<Student> selectAllStudent(String keyword,Integer modules,Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        return studentMapper.selectAllStudent(keyword,modules,page,limit);
    }


    @Override
    public List<Student> allStudent() {
        return studentMapper.allStudent();
    }

    //添加学生
    @Override
    public int addStudentInfo(Student student) {
        //添加联系人
        familyService.addFamilyInfo(student.getFamilyInfo());
        schoolService.addSchoolInfo(student.getSchoolInfo());
        //生成票据
        String s = Reader.addBill(student);
        //生成图片
        String ima = PDF2IMAGE.pdf2Image(s, "D:\\workspace\\education\\src\\main\\resources\\static\\pdfToImage", 300);
        student.getBill().setImage(ima);
        //插入票据表
        billService.addBillInfo(student.getBill());
        return studentMapper.addStudentInfo(student);
    }


    public Integer addScore(Integer sid, Integer scope) {
        return studentMapper.addScore(sid, scope);
    }

    public List<Area> selectArea() {
        return studentMapper.selectArea();
    }

    ;

    public List<TableDic> selectQu(Integer id) {
        return studentMapper.selectQu(id);
    }

    ;

    public List<TableDic> selectSchool(Integer id) {
        return studentMapper.selectSchool(id);
    }

    ;

    public String selectNumber(Integer id) {
        return studentMapper.selectNumber(id);
    }

    ;

    public CampusDic selectCNumber(Integer ca) {
        return studentMapper.selectCNumber(ca);
    }

    ;

    public Integer selectXuHao(Integer id) {
        return studentMapper.selectXuHao(id);
    }

    ;
}
