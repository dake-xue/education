package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class Area implements Serializable {

    private Integer aid;
    private String aname;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    @Override
    public String toString() {
        return "Area{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", count=" + count +
                '}';
    }
}
