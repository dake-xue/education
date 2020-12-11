package com.zhongsheng.education.entiy;

import java.util.List;

public class WinterStu {
    private Integer id;
    private String sname;
    private String sex;
    private String phone;
    private String idcard;
    private String schoolname;
    private String jiaofeijine;
    private Integer status;

    //票据(查询时用)
    private List<Bill> billList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getJiaofeijine() {
        return jiaofeijine;
    }

    public void setJiaofeijine(String jiaofeijine) {
        this.jiaofeijine = jiaofeijine;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WinterStu{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", schoolname='" + schoolname + '\'' +
                ", jiaofeijine='" + jiaofeijine + '\'' +
                ", status=" + status +
                ", billList=" + billList +
                '}';
    }
}
