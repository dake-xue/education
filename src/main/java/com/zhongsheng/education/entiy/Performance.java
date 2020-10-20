package com.zhongsheng.education.entiy;

import java.io.Serializable;
import java.util.Date;

public class Performance implements Serializable {
    private Integer id;
    private String snum;
    private Integer lunshu;
    private String classes;
    private Integer englishone;
    private Integer majorone;
    private Integer englishtwo;
    private Integer majortwo;
    private Integer englishthree;
    private Integer majorthree;
    private String comment;
    private String premark;
    private Date ptime;
    private  String attendance;

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public Integer getLunshu() {
        return lunshu;
    }

    public void setLunshu(Integer lunshu) {
        this.lunshu = lunshu;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Integer getEnglishone() {
        return englishone;
    }

    public void setEnglishone(Integer englishone) {
        this.englishone = englishone;
    }

    public Integer getMajorone() {
        return majorone;
    }

    public void setMajorone(Integer majorone) {
        this.majorone = majorone;
    }

    public Integer getEnglishtwo() {
        return englishtwo;
    }

    public void setEnglishtwo(Integer englishtwo) {
        this.englishtwo = englishtwo;
    }

    public Integer getMajortwo() {
        return majortwo;
    }

    public void setMajortwo(Integer majortwo) {
        this.majortwo = majortwo;
    }

    public Integer getEnglishthree() {
        return englishthree;
    }

    public void setEnglishthree(Integer englishthree) {
        this.englishthree = englishthree;
    }

    public Integer getMajorthree() {
        return majorthree;
    }

    public void setMajorthree(Integer majorthree) {
        this.majorthree = majorthree;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPremark() {
        return premark;
    }

    public void setPremark(String premark) {
        this.premark = premark;
    }
}
