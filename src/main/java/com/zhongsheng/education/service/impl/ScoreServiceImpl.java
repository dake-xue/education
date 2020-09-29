package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Score;
import com.zhongsheng.education.mapper.ScoreMapper;
import com.zhongsheng.education.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;

    public List<Score> selectScore(){
        return scoreMapper.selectScore();
    };

 public    Integer addScore(String name,Integer score){
    return scoreMapper.addScore(name,score);
 };

    public Integer deleteScore(Integer id){

        return scoreMapper.deleteScore(id);
    };

    public Integer updateScore(Integer id,String name,Integer score){
     return    scoreMapper.updateScore(id, name, score);

    };
}
