package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Familyinfo;
import com.zhongsheng.education.entiy.Schoolinfo;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.FamilyService;
import com.zhongsheng.education.service.SchoolService;
import com.zhongsheng.education.service.StudentService;
import com.zhongsheng.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    FamilyService familyService;
    @Autowired
    SchoolService schoolService;
    @Autowired
    private UserService userService;

    //查询所有学生(班级 classes/姓名 sname/专业 major)
    @RequestMapping(value = "/allStudent", method = RequestMethod.GET)
    public String selectAllStudent(String modules,String keyword) throws Exception {

        List<Student> studentList = studentService.selectAllStudent(modules, keyword);
        return "/allStudentInfo";
    }

    //学生详情页面
    @RequestMapping("/studentDetails")
    public String studentDetails(Integer sid, Model model) {
        Student student = studentService.selectStudent(sid);
        List<Familyinfo> familyInfoList = familyService.selectFamilyInfo(sid);
        List<Schoolinfo> schoolInfoList =schoolService.selectInfoInfo(sid);
        model.addAttribute("student",student);
        model.addAttribute("family",familyInfoList);
        model.addAttribute("school",schoolInfoList);
        return "";
    }

    ;

    /**
     * @创建人 xueke
     * @参数
     * @返回值
     * @创建时间 2020/9/17
     * @描述
     */
    //给学生添加积分
    @RequestMapping("/addScore")
    public Integer addScore(Integer sid, Integer scope) throws Exception {
        Integer student = studentService.addScore(sid, scope);
        if (student==1){
            return 1;
        }else{
            return 0;
        }
    };

    //添加学生
    @RequestMapping("/addStudent")
    public String addStudent(Student student) throws Exception {
        String pw = String.valueOf(student.getPhone());
        //截取手机号后六位
        String pwd = pw.substring(pw.length() - 6);
        System.out.println(pwd);
        //添加用户  学生whoid为2
        Integer i = userService.addUser(student.getPhone(), pwd, 2);
        //添加成功
        if (i == 1) {
            //获取新学生用户id
            User user = userService.selectUserId(2);
            Integer newSid = user.getUid();
            //把newSid赋值给sid
            student.setSid(newSid);
            //添加学生
           // Student stu=studentService.addStudent();
            //添加家长联系方式

            //添加学校联系方式

        }

        return "";
    }

}
