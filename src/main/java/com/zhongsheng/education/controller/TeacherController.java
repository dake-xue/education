package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.TeacherService;
import com.zhongsheng.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacherController")
public class TeacherController {
    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;

    //添加老师
    public Integer addTeacher(String tname,Integer username,String password){
        try {
            //添加用户
            Integer i= userService.addUser(username,password,1);
            //添加成功
            if(i==1){
                User user = userService.selectUserId(1);
                Integer j=teacherService.addTeacher(user.getUid(),tname);
            }else{
                return 0;
            }
         }catch (Exception e){
            return 0;
        }
        return 1;
    }
}
