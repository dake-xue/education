package com.zhongsheng.education.entiy;

import java.io.Serializable;


public class Familyinfo implements Serializable {

    private int fid;
    private String fname;
    private String relation;
    private int fphone;
    private int sfid;

    public int getSfid() {
        return sfid;
    }

    public void setSfid(int sfid) {
        this.sfid = sfid;
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

    public int getFphone() {
        return fphone;
    }

    public void setFphone(int fphone) {
        this.fphone = fphone;
    }


    @Override
    public String toString() {
        return "familyinfo{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", relation='" + relation + '\'' +
                ", fphone=" + fphone +
                '}';
    }
}
