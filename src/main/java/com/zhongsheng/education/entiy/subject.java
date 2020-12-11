package com.zhongsheng.education.entiy;

import java.io.Serializable;
import java.util.List;

public class subject implements Serializable {

    private Integer sid;
    private String subjectname;
    private List<RegularCollege> regularColleges;

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

    public List<RegularCollege> getRegularColleges() {
        return regularColleges;
    }

    public void setRegularColleges(List<RegularCollege> regularColleges) {
        this.regularColleges = regularColleges;
    }

    @Override
    public String toString() {
        return "subject{" +
                "sid=" + sid +
                ", subjectname='" + subjectname + '\'' +
                ", regularColleges=" + regularColleges +
                '}';
    }
}
