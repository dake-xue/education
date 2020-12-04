package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class School implements Serializable {
    private Integer id;
    private String name;
    private Integer yeardata;

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

    public Integer getYeardata() {
        return yeardata;
    }

    public void setYeardata(Integer yeardata) {
        this.yeardata = yeardata;
    }
}
