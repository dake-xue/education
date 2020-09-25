package com.zhongsheng.education.entiy;

import java.io.Serializable;


public class FamilyInfo implements Serializable {

    private int fid;
    private String fname;
    private String relation;
    private String fphone;
    private String snum;

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    @Override
    public String toString() {
        return "FamilyInfo{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", relation='" + relation + '\'' +
                ", fphone='" + fphone + '\'' +
                ", snum='" + snum + '\'' +
                '}';
    }
}
