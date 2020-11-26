package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class Customer implements Serializable {
    private Integer id;
    private String name;
    private Integer phone;
    private Integer qq;
    private String schoolname;
    private String major;
    private String period;
    private String remark;




    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", qq=" + qq +
                ", schoolname='" + schoolname + '\'' +
                ", major='" + major + '\'' +
                ", period='" + period + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
