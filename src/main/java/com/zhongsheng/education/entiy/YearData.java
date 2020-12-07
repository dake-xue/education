package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class YearData implements Serializable {

    private Integer id;
    private Integer year;
    private Integer data1;
    private Integer data2;
    private Integer data3;
    private Integer sid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getData1() {
        return data1;
    }

    public void setData1(Integer data1) {
        this.data1 = data1;
    }

    public Integer getData2() {
        return data2;
    }

    public void setData2(Integer data2) {
        this.data2 = data2;
    }

    public Integer getData3() {
        return data3;
    }

    public void setData3(Integer data3) {
        this.data3 = data3;
    }
}
