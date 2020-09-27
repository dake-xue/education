package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Area;
import com.zhongsheng.education.entiy.CampusDic;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.TableDic;
import com.zhongsheng.education.pdf.PDF2IMAGE;
import com.zhongsheng.education.pdf.Reader;
import com.zhongsheng.education.service.*;
import com.zhongsheng.education.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private BillService billService;

    @GetMapping("/allStudentInfo")
    @ResponseBody
    public String allStudentInfo(Integer modules, String keyword,Integer page,Integer limit)throws Exception{
        List<Student> studentList = studentService.selectAllStudent(keyword,modules,page,limit);
        //查询已交学费
        for (Student s:studentList){
            s.setJiaofeijine(studentService.selectJiaoFeiJinE(s.getSnum()));
        }
        return  DataUtil.layuiData(studentList);
    }
    //学生详情页面
    @RequestMapping("/studentDetails")
    public String studentDetails(String snum , Model model) {
        Student student = studentService.selectStudent(snum);
        model.addAttribute("student",student);
        return "studentDetails";
    }
    /**
     * @创建人 xueke
     * @参数
     * @返回值
     * @创建时间 2020/9/27
     * @描述 学生登陆后查看自己信息的方法
    */
    @RequestMapping("/stuToStudentDetails")
    public String stuToStuDetails(String phone , Model model){
        Student student = studentService.selectStudentByIphone(phone);
        System.out.println("**********************"+student);
        model.addAttribute("student",student);
        return "studentDetails";
    }

    /**
     * @创建人 xueke
     * @参数 
     * @返回值 
     * @创建时间 2020/9/17
     * @描述
    */
    //给学生添加积分
    @RequestMapping("/addScore")
    public Integer addScore(String snum, Integer scope) throws Exception {
        Integer student = studentService.addScore(snum, scope);
        if (student==1){
            return 1;
        }else{
            return 0;
        }
    };

    //添加学生
    @RequestMapping("/addStudent")
    @ResponseBody
    public String addStudent(Student student) throws Exception {
        //省份
        String anum = studentService.selectNumber(student.getArea());
        //校区
        CampusDic cnum=studentService.selectCNumber(student.getCampusid());
        //年份后两位
        String year = new SimpleDateFormat("yy", Locale.CHINESE).format(new Date());
        String num="0001";
        Integer in=studentService.selectXuHao(student.getCampusid());
        Integer n=1;
        if (in!=null && !" ".equals(in)){
             n=in+1;
             num=String.format("%04d",n);
        }
        //拼接学生id  （省份+校区+年份+序号）
        StringBuffer sr = new StringBuffer();
        sr.append(anum);
        sr.append(cnum.getCnum());
        sr.append(year);
        sr.append(num);
        String s=sr.toString();

        student.setSnum(s);
        student.setNumber(n);
        student.setCampus(cnum.getName());
        int i = studentService.addStudentInfo(student);

        return i+"";
    }


    //获取补款学生信息
    @RequestMapping("/selectOneStudent")
    public String selectOneStudent(String snum,Model model){
        Student student = studentService.selectStudent(snum);
        model.addAttribute("student",student);
        return "addMone";
    };
    //补款
    @RequestMapping("/addBill")
    public String addBill(Student student) {
        //生成票据
        String s = Reader.addBill(student);
        //生成图片
        String ima = PDF2IMAGE.pdf2Image(s, UrlUtil.getUrl()+"\\src\\main\\resources\\static\\pdfToImage", 300);
        student.getBill().setImage(ima);
        //插入票据表
        billService.addBillInfo(student.getBill());

        return "";
    }

    //查询省区校
    @RequestMapping("/selectArea")
    @ResponseBody
    public List<Area> selectArea() {
        List<Area> areaList = studentService.selectArea();
        return areaList;
    }

    @RequestMapping("/selectQu")
    @ResponseBody
    public List<TableDic> selectArea(Integer id) {
        List<TableDic> areaList = studentService.selectQu(id);
        return areaList;
    }

    @RequestMapping("/selectSchool")
    @ResponseBody
    public List<TableDic> selectSchool(Integer campus) {
        List<TableDic> areaList = studentService.selectSchool(campus);
        return areaList;
    }

    //积分兑换
    public Integer changeScore(String snum,Integer score){
      Integer i=studentService.changeScore(snum,score);
      return i;
    };

}
