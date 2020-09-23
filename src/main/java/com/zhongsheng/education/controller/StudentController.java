package com.zhongsheng.education.controller;

import com.alibaba.fastjson.JSON;
import com.zhongsheng.education.entiy.Familyinfo;
import com.zhongsheng.education.entiy.Schoolinfo;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.service.FamilyService;
import com.zhongsheng.education.service.SchoolService;
import com.zhongsheng.education.service.StudentService;
import com.zhongsheng.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private SchoolService schoolService;

    @RequestMapping("/allStudentInfo")
    @ResponseBody
    public String allStudentInfo(String modules, String keyword)throws Exception{
        List<Student> studentList = studentService.selectAllStudent(modules,keyword);
        Map< String,Object> map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count",1000);
        map.put("data",studentList);
        String studentinfo =  JSON.toJSONString(map);
        return  studentinfo;
    }

    //学生详情页面
    @RequestMapping("/studentDetails")
    public String studentDetails(Integer sid, Model model) {
        Student student = studentService.selectStudent(sid);
        List<Familyinfo> familyInfoList = familyService.selectFamilyInfo(sid);
        List<Schoolinfo> schoolInfoList =schoolService.selectInfoInfo(sid);
        model.addAttribute("student",student);
        model.addAttribute("family",familyInfoList);
        model.addAttribute("school",schoolInfoList);
        return "";
    };

    /**
     * @创建人 xueke
     * @参数 
     * @返回值 
     * @创建时间 2020/9/17
     * @描述
    */
    //给学生添加积分
    @RequestMapping("/addScore")
    public Integer addScore(Integer sid, Integer scope) throws Exception {
        Integer student = studentService.addScore(sid, scope);
        if (student==1){
            return 1;
        }else{
            return 0;
        }
    };

    //添加学生
    @RequestMapping("/addStudent")
    @ResponseBody
    public String addStudent(Student student) throws Exception {
        student.setSid(2233333);
        System.out.println(student);
        int i = studentService.addStudentInfo(student);

        return i+"";
    }

    

}
