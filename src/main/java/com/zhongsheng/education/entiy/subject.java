package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class subject implements Serializable {

    private Integer sid;
    private String subjectname;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    @Override
    public String toString() {
        return "subject{" +
                "sid=" + sid +
                ", subjectname='" + subjectname + '\'' +
                '}';
    }
}
