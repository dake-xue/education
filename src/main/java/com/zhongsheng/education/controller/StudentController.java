package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.Teacher;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.StudentService;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/StudentController")
public class StudentController {

    @Autowired
    private StudentService studentService;
    //查询所有学生(班级 classes/姓名 sname/专业 major)
    @RequestMapping("/selectAllStudent")
    public String selectAllStudent(String classes,String sname,String major) throws Exception {
        List<Student> studentList = studentService.selectAllStudent(classes,sname,major);

        return "";
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
