package com.zhongsheng.education.entiy;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {

    private int bid;
    private String snum;
    private int paymentAmount;
    private String image;
    private String remark;
    private Date intotime;
    private Integer area;
    private Integer campusid;
    private Integer schoolid;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCampusid() {
        return campusid;
    }

    public void setCampusid(Integer campusid) {
        this.campusid = campusid;
    }

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    public Date getIntotime() {
        return intotime;
    }

    public void setIntotime(Date intotime) {
        this.intotime = intotime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "bid=" + bid +
                ", snum='" + snum + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", image='" + image + '\'' +
                ", remark='" + remark + '\'' +
                ", intotime=" + intotime +
                ", area=" + area +
                ", campusid=" + campusid +
                ", schoolid=" + schoolid +
                '}';
    }
}
