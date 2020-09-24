package com.zhongsheng.education.entiy;

import java.util.Date;

public class Student {
    //学生id
    private int sid;
    //校区
    private String campus;

    //学校名称
    private String schoolname;
    //学生名字
    private String sname;

    //性别
    private String sex;
    //年级
    private String grade;
    //专业
    private String major;
    //手机号码
    private String phone;
    //班次
    private String classes;

    //缴费金额
    private int money;
    //考试年份
    private String examinationtime;
    //入学年份
    private String admissiontime;

    //报名日期
    private Date signupdate;
    //补费日期
    private Date addmoneydate;
    //缴费方式
    private String paymethod;
    //意向专业
    private String intentionmajor;
    //公共课
    private String publiccours;
    //专业课
    private String majorcours;
    //校区负责人
    private String campusmanager;
    //校园代理
    private String campusagent;
    //线上上课轮数
    private int onlinenumber;
    //线下上课轮数
    private int offlinenumber;
    //职务
    private String job;
    //收件家庭住址
    private String homeaddress;
    //身份证号
    private String idcard;
    //date生日
    private Date birthday;
    //民族
    private String nation;
    //备注
    private String remarks;
    //积分
    private int score;

    //考试科目
    private String subject;
    //院系
    private String department;

    //家庭联系方式
    private FamilyInfo familyInfo;
    //学校联系方式
    private SchoolInfo schoolInfo;
    //票据
    private Bill bill;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public FamilyInfo getFamilyInfo() {
        return familyInfo;
    }

    public void setFamilyInfo(FamilyInfo familyInfo) {
        this.familyInfo = familyInfo;
    }

    public SchoolInfo getSchoolInfo() {
        return schoolInfo;
    }

    public void setSchoolInfo(SchoolInfo schoolInfo) {
        this.schoolInfo = schoolInfo;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }



    public Date getSignupdate() {
        return signupdate;
    }

    public void setSignupdate(Date signupdate) {
        this.signupdate = signupdate;
    }

    public Date getAddmoneydate() {
        return addmoneydate;
    }

    public void setAddmoneydate(Date addmoneydate) {
        this.addmoneydate = addmoneydate;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getIntentionmajor() {
        return intentionmajor;
    }

    public void setIntentionmajor(String intentionmajor) {
        this.intentionmajor = intentionmajor;
    }

    public String getPubliccours() {
        return publiccours;
    }

    public void setPubliccours(String publiccours) {
        this.publiccours = publiccours;
    }

    public String getMajorcours() {
        return majorcours;
    }

    public void setMajorcours(String majorcours) {
        this.majorcours = majorcours;
    }

    public String getCampusmanager() {
        return campusmanager;
    }

    public void setCampusmanager(String campusmanager) {
        this.campusmanager = campusmanager;
    }

    public String getCampusagent() {
        return campusagent;
    }

    public void setCampusagent(String campusagent) {
        this.campusagent = campusagent;
    }

    public int getOnlinenumber() {
        return onlinenumber;
    }

    public void setOnlinenumber(int onlinenumber) {
        this.onlinenumber = onlinenumber;
    }

    public int getOfflinenumber() {
        return offlinenumber;
    }

    public void setOfflinenumber(int offlinenumber) {
        this.offlinenumber = offlinenumber;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getExaminationtime() {
        return examinationtime;
    }

    public void setExaminationtime(String examinationtime) {
        this.examinationtime = examinationtime;
    }

    public String getAdmissiontime() {
        return admissiontime;
    }

    public void setAdmissiontime(String admissiontime) {
        this.admissiontime = admissiontime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", campus='" + campus + '\'' +
                ", schoolname='" + schoolname + '\'' +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", grade='" + grade + '\'' +
                ", major='" + major + '\'' +
                ", phone='" + phone + '\'' +
                ", classes='" + classes + '\'' +
                ", money=" + money +
                ", examinationtime='" + examinationtime + '\'' +
                ", admissiontime='" + admissiontime + '\'' +
                ", signupdate=" + signupdate +
                ", addmoneydate=" + addmoneydate +
                ", paymethod='" + paymethod + '\'' +
                ", intentionmajor='" + intentionmajor + '\'' +
                ", publiccours='" + publiccours + '\'' +
                ", majorcours='" + majorcours + '\'' +
                ", campusmanager='" + campusmanager + '\'' +
                ", campusagent='" + campusagent + '\'' +
                ", onlinenumber=" + onlinenumber +
                ", offlinenumber=" + offlinenumber +
                ", job='" + job + '\'' +
                ", homeaddress='" + homeaddress + '\'' +
                ", idcard='" + idcard + '\'' +
                ", birthday=" + birthday +
                ", nation='" + nation + '\'' +
                ", remarks='" + remarks + '\'' +
                ", score=" + score +
                ", subject='" + subject + '\'' +
                ", department='" + department + '\'' +
                ", familyInfo=" + familyInfo +
                ", schoolInfo=" + schoolInfo +
                '}';
    }
}
