package com.zhongsheng.education.controller;

import com.alibaba.fastjson.JSON;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.Teacher;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.StudentService;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    //查询所有学生(班级 classes/姓名 sname/专业 major)
    @RequestMapping(value = "/allStudent",method = RequestMethod.GET)
    public String allStudent() throws Exception {

        return "/allStudentInfo";
    }

    @GetMapping("/allStudentInfo")
    @ResponseBody
    public String allStudentInfo()throws Exception{
        List<Student> studentList = studentService.allStudent();
        Map< String,Object> map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count",1000);
        map.put("data",studentList);
        String studentinfo =  JSON.toJSONString(map);
        return  studentinfo;
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

    

}
