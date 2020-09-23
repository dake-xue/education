package com.zhongsheng.education.entiy;

import java.io.Serializable;


public class FamilyInfo implements Serializable {

    private int fid;
    private String fname;
    private String relation;
    private String fphone;
    private int sfid;

    public FamilyInfo() {
    }

    public FamilyInfo(String fname, String relation, String fphone, int sfid) {
        this.fname = fname;
        this.relation = relation;
        this.fphone = fphone;
        this.sfid = sfid;
    }

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

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
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
