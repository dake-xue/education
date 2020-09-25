package com.zhongsheng.education.entiy;

public class SchoolInfo {
    private int scid;
    private String scname;
    private String screlation;
    private String scphone;
    private String snum;


    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname;
    }

    public String getScrelation() {
        return screlation;
    }

    public void setScrelation(String screlation) {
        this.screlation = screlation;
    }

    public String getScphone() {
        return scphone;
    }

    public void setScphone(String scphone) {
        this.scphone = scphone;
    }

    @Override
    public String toString() {
        return "SchoolInfo{" +
                "scid=" + scid +
                ", scname='" + scname + '\'' +
                ", screlation='" + screlation + '\'' +
                ", scphone='" + scphone + '\'' +
                ", snum='" + snum + '\'' +
                '}';
    }
}

