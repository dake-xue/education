package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.StudentService;
import com.zhongsheng.education.service.TeacherService;
import com.zhongsheng.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam Integer username, @RequestParam String password) throws Exception {
        //根据账号密码判断登录人
        User user = studentService.selectWho(username, password);
        //账号或密码错误
        if (user == null) {
            return "";
            //1==老师
        } else if (user.getWhoid() == 1) {
            String teacherName = teacherService.selectTeacherName(user.getUid());
            return teacherName;
            //2==学生
        } else if (user.getWhoid() == 2) {
            Student student = studentService.selectStudent(user.getUid());
            return user.getUid()+"";
        }
        return "error";
    }
}
