package com.zhongsheng.education.config;

import com.zhongsheng.education.entiy.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//HandlerInterceptor接口
public class LoginHandIterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取是否存在用户
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath()+"/user/toLogin");
            System.out.println("hfgshdfvbgfvuhduvjkdshiuue========================================");
            return false;
        } else {
            //已登录
            return true;
        }
    }


}
