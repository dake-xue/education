package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.Teacher;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.StudentService;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/StudentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //登录
    @RequestMapping("/login")
    public String login(Integer username, String password) throws Exception {
        System.out.println(username + "------------" + password);
        //根据账号密码判断登录人
        User user = studentService.selectWho(username, password);
        System.out.println(user);
        //账号或密码错误
        if (user == null) {
            System.out.println("12121212");
            return "账号或密码错误";
            //1==老师
        } else if (user.getWhoid() == 1) {
            System.out.println("老师");
            String teacher = studentService.selectTeacher(user.getUid());
            System.out.println(teacher + "====ttttttttttttttt");
            return teacher;
            //2==学生
        } else if (user.getWhoid() == 2) {
            System.out.println("学生");
            Student student = studentService.selectStudent(user.getUid());
            System.out.println(student + "====ssssssssssssssss");
            return student.toString();
        }
        return "";
    }
}
