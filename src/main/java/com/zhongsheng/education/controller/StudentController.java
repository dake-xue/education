package com.zhongsheng.education.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.*;
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
    public LayuiData allStudentInfo( @RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "3") int limit, Integer schoolid,SearchVo searchVo) throws Exception {
        Page pagehelper = PageHelper.startPage(page, limit);
        //取出session中的user
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        //把user中的area字段赋给search
        searchVo.setArea(loginUser.getArea());
        List<Student> studentList = studentService.selectAllStudent(searchVo,page,limit);
        //查询已交学费
        for (Student s : studentList) {
            s.setJiaofeijine(studentService.selectJiaoFeiJinE(s.getSnum()));
            Integer i = s.getMoney();
            Integer q = s.getJiaofeijine();
            Integer c = i - q;
            s.setWeijiaokuan(c);
        }
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

    //添加学生
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


    //补款
    @RequestMapping("/addBill")
    @ResponseBody
    public Integer addBill(String snum, Integer paymentAmount, String remarks, HttpSession session) {
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

        //生成票据
        String s = Reader.addBill(student1, user.getName(), erweima);
        //生成图片
        String ima = PDF2IMAGE.pdf2Image(s, UrlUtil.getUrl() + "\\src\\main\\resources\\static\\pdfToImage", 300);
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

}
