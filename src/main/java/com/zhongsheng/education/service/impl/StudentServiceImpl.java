package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.mapper.StudentMapper;
import com.zhongsheng.education.mapper.WinterStudentMapper;
import com.zhongsheng.education.pdf.BillNumber;
import com.zhongsheng.education.pdf.PDF2IMAGE;
import com.zhongsheng.education.pdf.Reader;
import com.zhongsheng.education.service.*;
import com.zhongsheng.education.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {


    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

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

    @Autowired
    private WinterStudentMapper winterStudentMapper;

    @Autowired
    private OrderService orderService;

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
       // student.setSchoolInfo(schoolService.selectSchoolInfo(snum));
        //票据
        student.setBillList(billService.selectBill(student.getSnum()));
        return student;
    }
    
    @Override
    public List<Performance> selectPer(String snum) {
        return studentMapper.selectPer(snum);
    }
    
    @Override
    public Student selectStudentOne(String snum) {
        //学生
        Student student = studentMapper.selectStudent(snum);
        //家庭
        student.setFamilyInfo(familyService.selectFamilyInfo(student.getSnum()));
        return student;
    }
    @Override
    public String selectJiaoFeiJinE(String snum) {
        List<Bill> billList = studentMapper.selectJiaoFeiJinE(snum);
        BigDecimal bigDecimal = new BigDecimal("0");
        for (int i = 0; i < billList.size(); i++) {
            bigDecimal.add(new BigDecimal(billList.get(i).getPaymentAmount()));
        }
        return bigDecimal.toString();
    }

    /**
     * @创建人 xueke
     * @创建时间 查询所有学生信息
     * @描述
    */
    @Override
    public List<Student> selectAllStudent(Integer schoolid, SearchVo searchVo) {
        List<Student> studentList =  studentMapper.selectAllStudent( schoolid, searchVo);
        //设置学生需要补款的金额
        for (Student stu : studentList) {
            BigDecimal money = new BigDecimal(stu.getMoney());
            BigDecimal jfje = new BigDecimal(stu.getJiaofeijine());
            BigDecimal wjje = money.subtract(jfje);
            stu.setWeijiaokuan(wjje.toString());
        }
        return studentList;
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

            //获取学校id
            TableDic tableDic = new TableDic();
            tableDic.setTableName("training_school_dic");
            tableDic.setName(student.getSchoolname());
            tableDic.setCampus_id(student.getCampusid());
            TableDic table = tableDicService.searchSchoolName(tableDic);
            student.setSchoolid(table.getId());

            int i = studentMapper.addStudentInfo(student);
            student.getFamilyInfo().setSnum(student.getSnum());
            //添加联系人
            familyService.addFamilyInfo(student.getFamilyInfo());
            //schoolService.addSchoolInfo(student.getSchoolInfo());
            //插入用户表
            User user = new User();
            user.setName(student.getSname());
            user.setUsername(student.getPhone());
            user.setPassword(student.getPhone().substring(student.getPhone().length() - 6));
            user.setRoleid(3);
            userService.addUser(user);
            if (i != 0) {
                log.info("添加用户完成。。。。。学号：" + student.getSnum());
                return student;
            }

        }

        log.info("已存在学号。。。。。。"+stu.getSnum());

        return stu;
    }

    //添加学生
    @Override
    public Integer addStudentTwo(Student student) {
            //省份
            String str = String.format("%02d", student.getArea());
            //校区
            CampusDic cnum = studentMapper.selectCNumber(student.getCampusid());
            log.info("参数为："+student.getCampusid()+"。校区信息为："+cnum);
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
            log.info("省份："+str+"。校区："+cnum.getCnum()+"。年份："+year+"。序号："+num);
            sr.append(str);
            sr.append(cnum.getCnum());
            sr.append(year);
            sr.append(num);
            String string = sr.toString();

            student.setSnum(string);
            student.setNumber(n);
            log.info("添加的校区名称为："+cnum.getName());

            student.setCampus(cnum.getName());
            //获取学校id
            TableDic tableDic = new TableDic();
            tableDic.setTableName("training_school_dic");
            tableDic.setName(student.getSchoolname());
            tableDic.setCampus_id(student.getCampusid());
            TableDic table = tableDicService.searchSchoolName(tableDic);
            student.setSchoolid(table.getId());
            log.info(student.getSname()+"是"+student.getCampus()+"的第"+num+"位学员。该校区id为"+student.getCampusid());

            log.info("学生信息："+student);
            int i = studentMapper.addStudentTwo(student);
            student.getFamilyInfo().setSnum(student.getSnum());
            //添加联系人
            familyService.addFamilyInfo(student.getFamilyInfo());
            // schoolService.addSchoolInfo(student.getSchoolInfo());
            //获取票据随机数0
            Bnumber billnumber= BillNumber.billNumber();
            //生成票据
            String s =  Reader.addBill(student, student.getCampusmanager(),billnumber);
            Bill bill = new Bill();
            boolean isWin = System.getProperty("os.name").toLowerCase().contains("win");
            String ima = "";
            //根据系统判断
            if (isWin){
                try {
                    ima =  PDF2IMAGE.pdf2Image(s, System.getProperty("user.dir")+"\\src\\main\\resources\\static\\pdfToImage", 300);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bill.setImage("\\zhongsheng\\pdfToImage\\"+MyUtil.getPngName(ima));
            }else {
                try {
                    ima = PDF2IMAGE.pdf2Image(s, "/usr/img", 300);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bill.setImage("\\zhongsheng\\pdfToImage\\"+MyUtil.linuxGetPngName(ima));
            }

            bill.setSnum(student.getSnum());
            bill.setRemark(student.getRemarks());
            bill.setArea(student.getArea());
            bill.setCampusid(student.getCampusid());
            bill.setSchoolid(student.getSchoolid());
            bill.setPaymentAmount(student.getJiaofeijine());
            bill.setIntotime(student.getSignupdate());
            bill.setBillnumber(billnumber.getBillnumber());
            //插入票据表
            billService.addBillInfo(bill);
            //插入用户表
            User user = new User();
            user.setName(student.getSname());
            user.setUsername(student.getPhone());
            user.setPassword(student.getPhone().substring(student.getPhone().length() - 6));
            user.setRoleid(3);
            userService.addUser(user);

        return i;
    }


    //添加学生
    @Override
    public Integer  addWinterStudent1(Student student)throws Exception{
        int i = winterStudentMapper.addWinterStudent1(student);
        student.setCampusmanager(" ");
        student.setSnum(student.getPhone());
        student.setArea(2);
        student.setFamilyInfo(new FamilyInfo());
        student.setSname(student.getSname());
        student.setJiaofeijine(student.getJiaofeijine());
        student.setSchoolname(student.getSchoolname());
        student.setSex(student.getSex());
        student.setPhone(student.getPhone());
        student.setIdcard(student.getIdcard());
        student.setRemarks("寒假班住宿费340+押金100");

        //获取票据随机数0
        Bnumber billnumber= BillNumber.billNumber();
        //生成票据
        String s =  Reader.addBill(student, student.getCampusmanager(),billnumber);
        Bill bill = new Bill();
        boolean isWin = System.getProperty("os.name").toLowerCase().contains("win");
        String ima = "";
        //根据系统判断
        if (isWin){
            ima =  PDF2IMAGE.pdf2Image(s, System.getProperty("user.dir")+"\\src\\main\\resources\\static\\pdfToImage", 300);
            bill.setImage("\\zhongsheng\\pdfToImage\\"+MyUtil.getPngName(ima));
        }else {
            ima = PDF2IMAGE.pdf2Image(s, "/usr/img", 300);
            bill.setImage("\\zhongsheng\\pdfToImage\\"+MyUtil.linuxGetPngName(ima));
        }
        bill.setSnum(student.getSnum());
        bill.setRemark(student.getRemarks());
        bill.setArea(student.getArea());
        bill.setCampusid(student.getCampusid());
        bill.setSchoolid(student.getSchoolid());
        bill.setPaymentAmount(student.getJiaofeijine());
        bill.setIntotime(student.getSignupdate());
        bill.setBillnumber(billnumber.getBillnumber());
        //插入票据表
        billService.addBillInfo(bill);
        //插入用户表
        User user = new User();
        user.setName(student.getSname());
        user.setUsername(student.getPhone());
        user.setPassword(student.getPhone().substring(student.getPhone().length() - 6));
        user.setRoleid(3);
        userService.addUser(user);

        return i;
    }


    /**
     * @创建人 xueke
     * @创建时间 2020/12/9
     * @描述 添加寒假班学生信息
    */
    @Override
    public Integer addWinterStudent(Student student) {
        return winterStudentMapper.addWinterStudent(student);
    }

    @Override
    public Integer addScore(String snum, Integer scope) {
        return studentMapper.addScore(snum, scope);
    }
    
    @Override
    public List<Area> selectArea() {
        return studentMapper.selectArea();
    }
    
    @Override
    public List<TableDic> selectQu(Integer id) {
        return studentMapper.selectQu(id);
    }
   
    @Override
    public List<TableDic> selectSchool(Integer id) {
        return studentMapper.selectSchool(id);
    }
   
    @Override
    public String selectNumber(Integer id) {
        return studentMapper.selectNumber(id);
    }
    
    @Override
    public CampusDic selectCNumber(Integer ca) {
        return studentMapper.selectCNumber(ca);
    }
   
    @Override
    public TableDic selectSchoolId(String name) {
        return studentMapper.selectSchoolId(name);
    }
    
    @Override
    public Integer selectXuHao(Integer id) {
        return studentMapper.selectXuHao(id);
    }
    
    @Override
    public Integer changeScore(String snum, Integer score) {
        return studentMapper.changeScore(snum, score);
    }

    @Override
    public Integer updateStudent(Student student) {
        familyService.updateFamily(student.getFamilyInfo());
        TableDic tableDic = new TableDic();
        tableDic.setId(student.getCampusid());
        tableDic.setTableName("campus_dic");
        student.setCampus(tableDicService.searchOne(tableDic).getName());
        //修改票据添加时间
        Bill bill = new Bill();
        bill.setPaymentAmount(student.getJiaofeijine());
        bill.setIntotime(student.getSignupdate());
        bill.setSnum(student.getSnum());
        int i =  billService.updateIntoTimeByMoneyAndSnum(bill);
        return studentMapper.updateStudent(student);
    }
    @Override
    public Integer addPer(Performance performance) {
        return studentMapper.addPer(performance);
    }
    
    @Override
    public Integer addPerfor(Performance performance) {
        return studentMapper.addPerfor(performance);
    }
   
    @Override
    public Performance selectPerOne(Integer id) {
        return studentMapper.selectPerOne(id);
    }

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

    @Override
    public List<WinterStu> allWinterStudent(String sname) {
        return winterStudentMapper.selectAll(sname);
    }

    @Override
    public WinterStu selectWinterStudentByPhone(String phone) {
        WinterStu student = winterStudentMapper.selectWinterStudentByPhone(phone);
        //票据(用寒假学生的手机号查询票据)
        student.setBillList(billService.selectBill(student.getPhone()));
        return student;
    }

    @Override
    public Integer updateWinterStatus(String phone) {
        return winterStudentMapper.updateWinterStatus(phone);
    }


}
