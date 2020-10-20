package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class Desk implements Serializable {
    private String month;
    private String val;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}