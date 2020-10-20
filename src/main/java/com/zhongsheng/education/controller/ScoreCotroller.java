package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Score;
import com.zhongsheng.education.service.ScoreService;
import com.zhongsheng.education.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/ScoreCotroller")
@Controller
public class ScoreCotroller {
    @Autowired
    ScoreService scoreService;

    //查询兑换
    @RequestMapping("/selectScore")
    @ResponseBody
    public String selectScore() {
        List<Score> scoreList = scoreService.selectScore();
        return MyUtil.layuiData(scoreList);
    }
    ;

    //添加兑换
    @RequestMapping("/addScore")
    @ResponseBody
    public Integer addScore(String name,Integer score) {
        Integer i = scoreService.addScore(name,score);
        return i;
    }

    //下架
    @RequestMapping("/deleteScore")
    @ResponseBody
    public Integer deleteScore(Integer id) {
        Integer integer = scoreService.deleteScore(id);
        return integer;
    }

    ;

    //修改兑换
    @RequestMapping("/updateScore")
    @ResponseBody
    public Integer updateScore(Integer id, String name, Integer score) {
        Integer integer = scoreService.updateScore(id, name, score);
        return integer;
    }

    ;
}
