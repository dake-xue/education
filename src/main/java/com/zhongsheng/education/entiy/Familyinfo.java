package com.zhongsheng.education.entiy;

public class Familyinfo {

    private int fid;
    private String fname;
    private String relation;
    private int fphone;

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
