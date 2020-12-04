package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class RegularCollege implements Serializable {

    private Integer id;
    private String name;
    private Integer junior;
    private Integer sid;
    private Integer  subject;
    private String subjectname;
    private String school;
    private Integer count;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJunior() {
        return junior;
    }

    public void setJunior(Integer junior) {
        this.junior = junior;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "RegularCollege{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", junior=" + junior +
                ", sid=" + sid +
                ", subject=" + subject +
                ", subjectname='" + subjectname + '\'' +
                ", school='" + school + '\'' +
                ", count=" + count +
                '}';
    }
}
