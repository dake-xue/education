package com.zhongsheng.education.entiy;

public class Schoolinfo {
    private int scid;
    private String scname;
    private String screlation;
    private int scphone;
    private int ssid;

    public int getSsid() {
        return ssid;
    }

    public void setSsid(int ssid) {
        this.ssid = ssid;
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

    public int getScphone() {
        return scphone;
    }

    public void setScphone(int scphone) {
        this.scphone = scphone;
    }

    @Override
    public String toString() {
        return "schoolinfo{" +
                "scid=" + scid +
                ", scname='" + scname + '\'' +
                ", screlation='" + screlation + '\'' +
                ", scphone=" + scphone +
                '}';
    }
}

