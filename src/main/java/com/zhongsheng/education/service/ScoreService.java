package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Score;

import java.util.List;

public interface ScoreService {
    List<Score> selectScore();
   Integer addScore(String name,Integer score);
    public Integer deleteScore(Integer id);
    public Integer updateScore(Integer id,String name,Integer score);
}
