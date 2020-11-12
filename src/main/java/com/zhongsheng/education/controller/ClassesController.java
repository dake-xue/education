package com.zhongsheng.education.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.Classes;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.ClassesService;
import com.zhongsheng.education.util.LayuiData;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/ClassesController")
@Controller
public class ClassesController {
    @Autowired
    ClassesService classesService;

    //获取当前登录人省份跳转班次页面
    @RequestMapping("/classes")
    public String deleteScore(Model model) {
        //取出session中的user
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        Integer area=loginUser.getArea();
        model.addAttribute("aid",area);
        return "allClasses";
    }


    @RequestMapping("/selectClasses")
    @ResponseBody
    public LayuiData selectClasses(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "3") int limit,Integer area) {
        Page pagehelper = PageHelper.startPage(page, limit);
        List<Classes> classesList = classesService.selectClasses(area);

        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(classesList);
        return layuiData;
    }

   //添加班次
    @RequestMapping("/addClass")
    @ResponseBody
    public Integer addScore(Integer aid,String name, Integer classmoney) {
        Integer i = classesService.addScore(aid,name,classmoney);
        return i;
    }

    //删除
    @RequestMapping("/deleteClass")
    @ResponseBody
    public Integer deleteScore(Integer id) {
        Integer integer = classesService.deleteScore(id);
        return integer;
    }

    ;

    //修改
    @RequestMapping("/updateClass")
    @ResponseBody
    public Integer updateScore(Integer id, String name, Integer classmoney) {
        Integer integer = classesService.updateScore(id, name, classmoney);
        return integer;
    }


    //查询班次
    @RequestMapping("/selectClass")
    @ResponseBody
    public List<Classes> selectClass(Integer id) {
        List<Classes> classesList = classesService.selectClasses(id);

        return classesList;
    }


    @RequestMapping("/selectClassMoney")
    @ResponseBody
    public List<Classes> selectClassMoney(String name) {
        List<Classes> classesList = classesService.selectClassMoney(name);
        return classesList;
    }


    //跳转专业课页面
    @RequestMapping("/course")
    public String course(Model model) {
        //取出session中的user
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        Integer area=loginUser.getArea();
        model.addAttribute("aid",area);
        return "allCourse";
    }

    //专业课管理
    @RequestMapping("/selectCourse")
    @ResponseBody
    public LayuiData selectCourse(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "3") int limit,Integer area) {
        Page pagehelper = PageHelper.startPage(page, limit);
        List<Classes> classesList = classesService.selectCourse(area);

        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(classesList);
        return layuiData;
    }

    //添加专业课
    @RequestMapping("/addCourse")
    @ResponseBody
    public Integer addCourse(Integer aid,String name) {
        Integer i = classesService.addCourse(aid,name);
        return i;
    }
    //删除专业课
    @RequestMapping("/deleteCourse")
    @ResponseBody
    public Integer deleteCourse(Integer id) {
        Integer integer = classesService.deleteCourse(id);
        return integer;
    }

    ;

    //修改专业课
    @RequestMapping("/updateCourse")
    @ResponseBody
    public Integer updateCourse(Integer id, String name) {
        Integer integer = classesService.updateCourse(id, name);
        return integer;
    }
}
