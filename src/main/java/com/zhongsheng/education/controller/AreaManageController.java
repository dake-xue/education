package com.zhongsheng.education.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.Area;
import com.zhongsheng.education.entiy.CampusDic;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.TableDic;
import com.zhongsheng.education.service.AreaManageService;
import com.zhongsheng.education.service.StudentService;
import com.zhongsheng.education.util.LayuiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/areaManage")
@Controller
public class AreaManageController {
    @Autowired
    AreaManageService areaManageService;
    @Autowired
    StudentService studentService;


    //查询省区校
    @RequestMapping("/selectArea")
    @ResponseBody
    public LayuiData selectArea(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "6") int limit)throws Exception{
        Page pagehelper= PageHelper.startPage(page,limit);
        List<Area> areaList = studentService.selectArea();
        for (Area a:areaList){
            List<TableDic> tableDicList=studentService.selectQu(a.getAid());
            a.setCount(tableDicList.size());
        }

        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int)pagehelper.getTotal());
        layuiData.setData(areaList);
        System.out.println(areaList);
        return  layuiData;
    }

    @RequestMapping("/selectQu")
    public String selectArea(Integer id, Model model) {
        model.addAttribute("aid",id);
        return "quManage";
    }

    @RequestMapping("/selectCampus")
    @ResponseBody
    public LayuiData selectArea(Integer id,@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "6") int limit)throws Exception{
        Page pagehelper= PageHelper.startPage(page,limit);
        List<TableDic> areaList = studentService.selectQu(id);
        for (TableDic a:areaList){
            List<TableDic> tableDicList=studentService.selectSchool(a.getId());
            a.setCount(tableDicList.size());
        }
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int)pagehelper.getTotal());
        layuiData.setData(areaList);
        System.out.println(areaList);
        return  layuiData;
    }
    @RequestMapping("/selectXiao")
    public String selectSchool(Integer id,Model model) {
        model.addAttribute("id",id);
        return "xiaoManage";
    }

    @RequestMapping("/selectSchool")
    @ResponseBody
    public LayuiData selectSchool(Integer id,@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "6") int limit)throws Exception{
        Page pagehelper= PageHelper.startPage(page,limit);
        List<TableDic> tableDicList = studentService.selectSchool(id);
        for (TableDic a:tableDicList){
            List<Student> t=areaManageService.selectStudent(a.getId());
            a.setCount(t.size());
        }
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int)pagehelper.getTotal());
        layuiData.setData(tableDicList);
        return  layuiData;
    }

    //添加省份
    @RequestMapping("/addArea")
    @ResponseBody
    public String addArea(String name) throws Exception {
        Integer student = areaManageService.addArea(name);
        Area area=areaManageService.selectArea(name);
        //省份
        String str = String.format("%02d",area.getAid());
        System.out.println("省份——————"+str);
        Integer a=areaManageService.updateArea(area.getAid(),str);
        if (student==1 && a==1){
            return "yes";
        }else {
            return "no";
        }

    }

    @RequestMapping("/selectStudent")
    public String selectStudent(Integer schoolid,Model model) {
        model.addAttribute("schoolid",schoolid);
        return "allStudentInfo";
    }

    //添加校区
    @RequestMapping("/addCampus")
    @ResponseBody
    public String addCampus(Integer aid,String name) throws Exception {
        String num="01";
        Integer in=areaManageService.selectCnum(aid);
        Integer n=1;
        if (in!=null && !" ".equals(in)){
            n=in+1;
            num=String.format("%02d",n);
        }
        Integer integer=areaManageService.addCampus(aid,num,name);
        if (integer==1){
            return "yes";
        }else {
            return "no";
        }
    }

    //添加学校
    @RequestMapping("/addSchool")
    @ResponseBody
    public String addSchool(Integer id,String name) throws Exception {
        Integer integer=areaManageService.addSchool(id,name);
        if (integer==1){
            return "yes";
        }else {
            return "no";
        }
    }


    //修改省份
    @RequestMapping("/UpdateArea")
    @ResponseBody
    public String UpdateArea(Area area) throws Exception {
        Integer integer=areaManageService.toUpdateArea(area);
        if (integer==1){
            return "yes";
        }else {
            return "no";
        }
    }

    //修改校区
    @RequestMapping("/UpdateCampus")
    @ResponseBody
    public String UpdateCampus(CampusDic campusDic) throws Exception {
        Integer integer=areaManageService.toUpdateCampus(campusDic);
        if (integer==1){
            return "yes";
        }else {
            return "no";
        }
    }

    //修改学校
    @RequestMapping("/UpdateSchool")
    @ResponseBody
    public String UpdateSchool(CampusDic campusDic) throws Exception {
        Integer integer=areaManageService.toUpdateSchool(campusDic);
        if (integer==1){
            return "yes";
        }else {
            return "no";
        }
    }

    //查询所有
    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        List<Area> areaList = areaManageService.allArea();
        return JSON.toJSONString(areaList);
    }
}
