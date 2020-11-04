package com.zhongsheng.education.service.impl;

import com.zhongsheng.EducationApplication;
import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.mapper.StudentMapper;
import com.zhongsheng.education.pdf.PDF2IMAGE;
import com.zhongsheng.education.pdf.Reader;
import com.zhongsheng.education.service.*;
import com.zhongsheng.education.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {


    private static final Logger log = LoggerFactory.getLogger(EducationApplication.class);

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private SchoolService schoolService;
    @Autowired
    private BillService billService;

    @Autowired
    private UserService userService;

    @Autowired
    private TableDicService tableDicService;

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

    public List<Performance> selectPer(String snum) {
        return studentMapper.selectPer(snum);
    }

    public Student selectStudentOne(String snum) {
        //学生
        Student student = studentMapper.selectStudent(snum);
        //家庭
        student.setFamilyInfo(familyService.selectFamilyInfo(student.getSnum()));
        return student;
    }

    public String selectJiaoFeiJinE(String snum) {
        List<Bill> billList = studentMapper.selectJiaoFeiJinE(snum);
        BigDecimal bigDecimal = new BigDecimal("0");
        for (int i = 0; i < billList.size(); i++) {
            bigDecimal.add(new BigDecimal(billList.get(i).getPaymentAmount()));
        }
        return bigDecimal.toString();
    }


    @Override
    public List<Student> selectAllStudent(Integer schoolid, SearchVo searchVo) {

        return studentMapper.selectAllStudent( schoolid, searchVo);
    }

    //添加学生
    @Override
    public Student addStudentInfo(Student student, String name) {
        Student stu = studentMapper.selectStudentBySnameAndIphone(student);
        if (stu == null) {
            //省份
            String str = String.format("%02d", student.getArea());
            //校区
            CampusDic cnum = studentMapper.selectCNumber(student.getCampusid());
            //年份后两位
            String year = new SimpleDateFormat("yy", Locale.CHINESE).format(new Date());
            String num = "0001";
            Integer in = studentMapper.selectXuHao(student.getCampusid());
            Integer n = 1;
            if (in != null && !" ".equals(in)) {
                n = in + 1;
                num = String.format("%04d", n);
            }
            //拼接学生id  （省份+校区+年份+序号）
            StringBuffer sr = new StringBuffer();
            sr.append(str);
            sr.append(cnum.getCnum());
            sr.append(year);
            sr.append(num);
            String string = sr.toString();

            student.setSnum(string);
            student.setNumber(n);
            student.setCampus(cnum.getName());

            int i = studentMapper.addStudentInfo(student);
            student.getSchoolInfo().setSnum(student.getSnum());
            student.getFamilyInfo().setSnum(student.getSnum());
            //添加联系人
            familyService.addFamilyInfo(student.getFamilyInfo());
            schoolService.addSchoolInfo(student.getSchoolInfo());
            log.info("=======================" + student.toString());
            /*//生成票据
            String s = Reader.addBill(student, name);
            //生成图片
            String ima = PDF2IMAGE.pdf2Image(s, System.getProperty("user.dir") + "\\src\\main\\resources\\static\\pdfToImage", 300);
            Bill bill = new Bill();
            bill.setImage("\\zhongsheng\\pdfToImage\\" + MyUtil.getPngName(ima));
            bill.setSnum(student.getSnum());
            bill.setRemark(student.getRemarks());
            bill.setArea(student.getArea());
            bill.setCampusid(student.getCampusid());
            bill.setSchoolid(student.getSchoolid());
            //插入票据表
            billService.addBillInfo(bill);*/
            //插入用户表
            User user = new User();
            user.setName(student.getSname());
            user.setUsername(student.getPhone());
            user.setPassword(student.getPhone().substring(student.getPhone().length() - 6));
            user.setRoleid(3);
            userService.addUser(user);
            if (i != 0) {
                log.info("添加用户完成。。。。。。" + i + "******学号：" + student.getSnum());
                return student;
            }

        }

        log.info("已存在学号。。。。。。"+stu.getSnum());

        return stu;
    }

    public Integer addScore(String snum, Integer scope) {
        return studentMapper.addScore(snum, scope);
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

    public TableDic selectSchoolId(String name) {
        return studentMapper.selectSchoolId(name);
    }



    public Integer selectXuHao(Integer id) {
        return studentMapper.selectXuHao(id);
    }
    public Integer changeScore(String snum, Integer score) {
        return studentMapper.changeScore(snum, score);
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

    public Integer addPer(Performance performance) {
        return studentMapper.addPer(performance);
    }

    public Integer addPerfor(Performance performance) {
        return studentMapper.addPerfor(performance);
    }

    ;

    public Performance selectPerOne(Integer id) {
        return studentMapper.selectPerOne(id);
    }

    ;

    @Override
    public Integer updateStatus(Student student) {
        return studentMapper.updateStatus(student);
    }

    @Override
    public Student selectStudentBySnameAndIphone(Student student) {
        return studentMapper.selectStudentBySnameAndIphone(student);
    }

    @Override
    public List<Student> searchStuByCamp(String name,Integer page, Integer limit) {
        return studentMapper.searchStuByCamp(name,page,limit);
    }

}
