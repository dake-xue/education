package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class CampusDic implements Serializable {
    private Integer id;
    private String name;
    private String cnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    @Override
    public String toString() {
        return "CampusDic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnum='" + cnum + '\'' +
                '}';
    }
}
