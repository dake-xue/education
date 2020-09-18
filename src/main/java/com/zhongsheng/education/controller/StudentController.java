package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.Teacher;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.StudentService;
import com.zhongsheng.education.service.UserService;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;
    //查询所有学生(班级 classes/姓名 sname/专业 major)
    @RequestMapping(value = "/allStudent",method = RequestMethod.GET)
    public String selectAllStudent(String classes,String sname,String major) throws Exception {
        List<Student> studentList = studentService.selectAllStudent(classes,sname,major);
        return "/allStudentInfo";
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
    public String addScore(Integer sid,Integer scope) throws Exception {
        Student student= studentService.addScore(sid,scope);
        return "";
    }


    //添加学生
    @RequestMapping("/addStudent")
    public String addStudent(Student student) throws Exception {

        String pw = String.valueOf(student.getPhone());
        //截取手机号后六位
        String pwd = pw.substring(pw.length() - 6);
        System.out.println(pwd);
        //添加用户
        Integer i = userService.addUser(student.getPhone(), pwd);
        //添加成功
        if (i == 1) {
            //获取新用户id
            User user = userService.selectUserId();
            Integer newSid = user.getUid();
            //把newSid赋值给sid
            student.setSid(newSid);
            //添加学生


        }

        return "";
    }

    

}
