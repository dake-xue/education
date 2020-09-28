package com.zhongsheng.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.mapper.StudentMapper;
import com.zhongsheng.education.pdf.PDF2IMAGE;
import com.zhongsheng.education.pdf.Reader;
import com.zhongsheng.education.service.*;
import com.zhongsheng.education.util.UrlUtil;
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

    @Autowired
    private TableDicService  tableDicService;

    /**
     * @创建人 xueke
     * @参数
     * @返回值
     * @创建时间 2020/9/27
     * @描述 学生登陆后调用的方法（查看自己的详细信息）
    */
    @Override
    public Student selectStudentByIphone(String phone) {
        Student student = studentMapper.selectStudentByIphone(phone);
        //家庭和学校
        student.setFamilyInfo(familyService.selectFamilyInfo(student.getSnum()));
        student.setSchoolInfo(schoolService.selectSchoolInfo(student.getSnum()));
        //票据
        student.setBillList(billService.selectBill(student.getSnum()));
        return student;
    }

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
        student.setFamilyInfo(familyService.selectFamilyInfo(snum));
        student.setSchoolInfo(schoolService.selectSchoolInfo(snum));
        //票据
        student.setBillList(billService.selectBill(student.getSnum()));

        return student;
    }

    @Override
    public Student selectStudentID(Integer id) {
        return studentMapper.selectStudentID(id);
    }



    @Override
    public List<Student> selectAllStudent(String keyword,Integer modules,Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        return studentMapper.selectAllStudent(keyword,modules,page,limit);
    }


    //添加学生
    @Override
    public int addStudentInfo(Student student) {
        int i = studentMapper.addStudentInfo(student);
        System.out.println(student.getSnum()+"***************");
        student.getSchoolInfo().setSnum(student.getSnum());
        student.getFamilyInfo().setSnum(student.getSnum());
        //添加联系人
        familyService.addFamilyInfo(student.getFamilyInfo());
        schoolService.addSchoolInfo(student.getSchoolInfo());
        //生成票据
        String s = Reader.addBill(student);
        //生成图片
        String ima = PDF2IMAGE.pdf2Image(s, UrlUtil.getUrl()+"\\src\\main\\resources\\static\\pdfToImage", 300);
        student.getBill().setImage(ima);
        student.getBill().setSnum(student.getSnum());
        //插入票据表
        billService.addBillInfo(student.getBill());
        return i;
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

    @Override
    public Integer updateStudent(Student student) {
        schoolService.updateSchool(student.getSchoolInfo());
        familyService.updateFamily(student.getFamilyInfo());
        TableDic tableDic = new TableDic();
        tableDic.setId(student.getCampusid());
        tableDic.setTableName("campus_dic");
        student.setCampus(tableDicService.searchOne(tableDic).getName());
        return studentMapper.updateStudent(student);
    }

}
