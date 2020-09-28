package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ScoreMapper {
    @Select("select * from score")
    public List<Score> selectScore();

    @Insert("insert into score values(#{name},#{score})")
    public Integer addScore(Score score);

    @Update("update score set status=#{status} where id=#{id}")
    public Integer deleteScore(Integer id,Integer status);

    @Update("update score set name=#{name},score=#{score} where id=#{id}")
    public Integer updateScore(Integer id, String name, Integer score);
}
