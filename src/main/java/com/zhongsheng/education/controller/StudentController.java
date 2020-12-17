package com.zhongsheng.education.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.pdf.BillNumber;
import com.zhongsheng.education.pdf.PDF2IMAGE;
import com.zhongsheng.education.pdf.QrCodeTest;
import com.zhongsheng.education.pdf.Reader;
import com.zhongsheng.education.service.*;
import com.zhongsheng.education.util.LayuiData;
import com.zhongsheng.education.util.MyUtil;
import com.zhongsheng.education.util.UrlUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequestMapping("/student")
@Controller
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

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
    public LayuiData allStudentInfo( @RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "3") int limit,
                                     Integer schoolid,SearchVo searchVo) throws Exception {
        //取出session中的user
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        //把user中的area字段赋给search
        searchVo.setArea(loginUser.getArea());
        Page pagehelper = PageHelper.startPage(page, limit);
        List<Student> studentList = studentService.selectAllStudent(schoolid,searchVo);
        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(studentList);
        return layuiData;
    }

    //学生详情页面
    @RequestMapping("/studentDetails")
    public String studentDetails(String snum , Model model) {
        Student student = studentService.selectStudent(snum);
        List<Performance> performance=studentService.selectPer(snum);
        model.addAttribute("performance",performance);
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
        model.addAttribute("student",student);
        return "stuToStudentDetails";
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
    @ResponseBody
    public Integer addScore(String snum, Integer score) throws Exception {
        Integer student = studentService.addScore(snum, score);
        if (student==1){
            return 1;
        }else{
            return 0;
        }
    };

    /**
     * @创建人 xueke
     * @创建时间 添加学生
     * @描述
    */
   @RequestMapping(value = "/addStudent",method = RequestMethod.POST )
    public String addStudent(Student student, RedirectAttributes attr) throws Exception {
        Student stu = studentService.addStudentInfo(student,student.getCampusmanager());
        if(stu!=null){
            attr.addAttribute("goods_name",stu.getIntentionmajor()+"："+student.getClasses());
            attr.addAttribute("price",student.getJiaofeijine());
            attr.addAttribute("order_number",MyUtil.getOrderName());
            attr.addAttribute("sNum",stu.getSnum());
            attr.addAttribute("area",stu.getArea());
            return "redirect:/alipay/toPay";
        }
        return "error";
    }

    @RequestMapping("/addStudentTwo")
    @ResponseBody
    public String addStudentTwo(Student student) throws Exception {
        Integer integer = studentService.addStudentTwo(student);
        if (integer!=0){
            return "yes";
        }
        return "no";
    }

    /**
     * @创建人 xueke
     * @创建时间 2020/12/9
     * @描述 添加寒假班的学生信息
    */
    @RequestMapping("/addWinterStudent")
    public String addWinterStudent(Student student,RedirectAttributes attr) throws Exception {
        Integer integer = studentService.addWinterStudent(student);
        //设置票据所需内容
        student.setFamilyInfo(new FamilyInfo());
        if(integer!=0){
            attr.addAttribute("goods_name","中升教育河南分校寒假班住宿费");
            attr.addAttribute("price",student.getJiaofeijine());
            attr.addAttribute("order_number",MyUtil.getOrderName());
            attr.addAttribute("sNum",student.getPhone());
            attr.addAttribute("area",2);
            return "redirect:/alipay/toPay";
        }
        return "error";
    }
    @RequestMapping("/addWinterStudent1")
    @ResponseBody
    public String addWinterStudent1(Student student) throws Exception {
        Integer integer = studentService.addWinterStudent1(student);
        if (integer!=0){
            return "yes";
        }
        return "no";
    }


    //补款
    @RequestMapping("/addBill")
    @ResponseBody
    public Integer addBill(String snum, String paymentAmount, String remarks, HttpSession session) {
        User user=(User)session.getAttribute("user");
        Student student1 = studentService.selectStudentOne(snum);
        Bill bill=new Bill();
        bill.setSnum(snum);
        bill.setPaymentAmount(paymentAmount);
        bill.setRemark(remarks);
        student1.setBill(bill);
        student1.setRemarks(remarks);
        student1.setJiaofeijine(paymentAmount);
        //生成二维码
        String erweima = QrCodeTest.erweima(snum);
        //获取票据随机数
        Bnumber billnumber= BillNumber.billNumber();
        //生成票据
        String s = Reader.addBill(student1, user.getName(),billnumber);
        //生成图片
        String ima = null;
        try {
            ima = PDF2IMAGE.pdf2Image(s, UrlUtil.getUrl() + "\\src\\main\\resources\\static\\pdfToImage", 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        student1.getBill().setImage(ima);
        //插入票据表

         Integer i=billService.addBillInfo(student1.getBill());
           return i;
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

    @RequestMapping("/toUpdateStudent")
    public String toUpdateStudent(String snum , Model model){
        Student student = studentService.selectStudent(snum);
        model.addAttribute("student",student);
        return "updateStudent";
    }

    @RequestMapping("/updateStudent")
    @ResponseBody
    public String updateStudent(Student student){
        int i = studentService.updateStudent(student);
        if (i==1){
            return "yes";
        }
        return "no";
    }

    //查询成绩
    @RequestMapping("/selectPerOne")
    public String selectPerOne(Integer id,Model model){
        Performance performance=studentService.selectPerOne(id);
        model.addAttribute("performance",performance);
        return "updatePer";
    }

    //添加成绩
    @RequestMapping("/addPer")
    @ResponseBody
    public String addPer(Performance performance){
        Integer i = studentService.addPer(performance);
        if (i==1){
            return "yes";
        }
        return "no";
    }

    //补充成绩
    @RequestMapping("/addPerfor")
    @ResponseBody
    public Integer addPerfor(Performance performance){
        Integer i = studentService.addPerfor(performance);
            return i;
    }

    //查询该用户招了多少名学生
    @RequestMapping("/searchStuByCamp")
    @ResponseBody
    public LayuiData searchStuByCamp(String name,Integer page,Integer limit){
        Page pagehelper= PageHelper.startPage(page,limit);
        List<Student> list = studentService.searchStuByCamp(name,page,limit);
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int)pagehelper.getTotal());
        layuiData.setData(list);
        return layuiData;

    }

    /**
     * @创建人 xueke
     * @创建时间 2020/12/9
     * @描述 所有寒假班学生信息
    */
    @RequestMapping("/allWinterStudent")
    @ResponseBody
    public LayuiData allWinterStudent(String sname,Integer page,Integer limit){
        Page pagehelper= PageHelper.startPage(page,limit);
        List<WinterStu> list = studentService.allWinterStudent(sname);
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int)pagehelper.getTotal());
        layuiData.setData(list);
        return layuiData;
    }


    /**
     * @创建人 xueke
     * @创建时间 2020/12/9
     * @描述 寒假班学生详细信息
    */
    @RequestMapping("/winterStudentMsg")
    public String winterStudentMsg(String phone , Model model) {
        WinterStu student = studentService.selectWinterStudentByPhone(phone);
        model.addAttribute("student",student);
        return "winterStudentDetails";
    }

    @RequestMapping("/select")
    @ResponseBody
    public String selectUrl(Integer campus) {
        return "地址是："+UrlUtil.getUrl();
    }



}
