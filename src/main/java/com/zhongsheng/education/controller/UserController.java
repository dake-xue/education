package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.UserService;
import com.zhongsheng.education.util.MyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * @创建人 xueke
     * @创建时间 2020/9/25
     * @描述 退出登录
    */
    @RequestMapping("/loginOut")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
            System.out.println("已退出登录。。。。。。");
        }
        return "redirect:/user/toLogin";
    }
    /**
     * @创建人 xueke
     * @创建时间 2020/9/27
     * @描述 展示所有用户信息
    */
    @RequestMapping("/allUser")
    @ResponseBody
    public String allUser(Integer page,Integer limit){
        List<User> userList =  userService.selectAllUser(page, limit);
        return MyUtil.layuiData(userList);
    }

    /**
     * @创建人 xueke
     * @创建时间 2020/9/29
     * @描述 添加用户
    */
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User user){
        user.setPassword(MyUtil.getPassWord(user.getUsername()));
        int i =  userService.addUser(user);
        if(i==1){return "yes";}
        return "no";
    }

    /**
     * @创建人 xueke
     * @创建时间 2020/9/29
     * @描述 修改密码
    */
    @RequestMapping("/updPass")
    @ResponseBody
    public String updatePass(User user){
        int i =  userService.updatePass(user);
        if(i==0){
            return "no";
        }
        return "yes";
    }

    /**
     * 用户登录接口
     * @param user user
     * @param
     * @return string
     */
    @PostMapping("/login")
    public String login(User user, Model model){
        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        try {
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            //根据当前登录账号来获取当前当前账号所拥有权限的菜单列表
            String username = subject.getPrincipal().toString();
            logger.info("对用户[" + user.getUsername()+ "]进行登录验证..验证通过......");
            return "redirect:/student/toAllStudent";
        }catch(UnknownAccountException uae){
            logger.info("对用户[" + user.getUsername()+ "]进行登录验证..验证未通过,未知账户");
            return "redirect:/user/toLogin";//返回登录页面
        }catch(IncorrectCredentialsException ice){
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,密码不正确");
            return "redirect:/user/toLogin";//返回登录页面
        }catch(LockedAccountException lae){
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,账户已锁定");
            return "redirect:/user/toLogin";//返回登录页面
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,错误次数过多");
            return "redirect:/user/toLogin";//返回登录页面
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            return "redirect:/user/toLogin";//返回登录页面
        }
        }


    @RequestMapping("/updStatus")
    @ResponseBody
    public String updateStatus(User user){
        int i =  userService.updateUserStatus(user);
        if(i==0){
            return "no";
        }
        return "yes";
    }

}
