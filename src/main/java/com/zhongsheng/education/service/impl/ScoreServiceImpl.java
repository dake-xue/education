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

 public    Integer addScore(Score score){
    return scoreMapper.addScore(score);
 };

    public Integer deleteScore(Integer id,Integer status){

        return scoreMapper.deleteScore(id,status);
    };

    public Integer updateScore(Integer id,String name,Integer score){
     return    scoreMapper.updateScore(id, name, score);

    };
}
