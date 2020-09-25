package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.StudentService;
import com.zhongsheng.education.service.UserService;
import com.zhongsheng.education.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * @创建人 xueke
     * @参数
     * @返回值
     * @创建时间 2020/9/25
     * @描述 登录
    */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, HttpSession session) throws Exception {
        //根据账号密码判断登录人
        User loginUser = userService.selectWho(user);
        //账号或密码错误
        if (loginUser == null) {
            return "";
            //1==老师
        } else if (loginUser.getWhoid() == 1) {
            session.setAttribute("user",loginUser);
            return "redirect:/student/toAllStudent";
            //2==学生
        } else if (loginUser.getWhoid() == 2) {
            session.setAttribute("user",loginUser);
            return user.getUid()+"";
        }
        return "error";
    }
    /**
     * @创建人 xueke
     * @参数
     * @返回值
     * @创建时间 2020/9/25
     * @描述 退出登录
    */
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/user/toLogin";
    }

    @RequestMapping("/allUser")
    @ResponseBody
    public String allUser(Integer page,Integer limit){
        List<User> userList =  userService.selectAllUser(page, limit);
        return DataUtil.layuiData(userList);
    }

}
