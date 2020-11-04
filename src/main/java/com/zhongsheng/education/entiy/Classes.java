package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class Classes implements Serializable {
    private Integer id;
    private Integer aid;
    private String name;
    private String classmoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassmoney() {
        return classmoney;
    }

    public void setClassmoney(String classmoney) {
        this.classmoney = classmoney;
    }
}
