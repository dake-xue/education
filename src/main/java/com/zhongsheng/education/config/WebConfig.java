package com.zhongsheng.education.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //跳转所有学生信息页
        registry.addViewController("/student/toAllStudent").setViewName("allStudentInfo");
        //跳转添加学生信息页
        registry.addViewController("/student/toAddStudent").setViewName("addStudent");
        //跳转登录页
        registry.addViewController("/user/toLogin").setViewName("index");
        //跳转所有用户页面
        registry.addViewController("/user/toAllUser").setViewName("allUser");
        //跳转积分管理页面
        registry.addViewController("/ScoreCotroller/toScore").setViewName("allScore");
        //跳转学生详情页面
        //registry.addViewController("/student/toStudentDetails").setViewName("studentDetails");
    }

    /**
     * @创建人 xueke
     * @参数
     * @返回值
     * @创建时间 2020/9/24
     * @描述 添加拦截器
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，registry.addInterceptor(new LoginHandlerInterceptor())
        //处理拦截那些东西.addPathPatterns("/**")
        //排除那些请求 excludePatterns()
        registry.addInterceptor(new LoginHandIterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/static/**","/user/**",
                        "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                        "/**/*.jpeg", "/**/*.gif", "/**/fonts/*");
    }






    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 此方法解决前台提交的日期参数绑定不正确问题,将自己实现的StringToDateConverter交给spring,让其知道如何进行处理
     */
    @PostConstruct //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
    public void initEditableValidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }

    //添加虚拟路径映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pdfToImage/**").addResourceLocations("file:"+System.getProperty("user.dir")+"\\src\\main\\resources\\static\\pdfToImage\\");
    }
}
