package com.zhongsheng.education.entiy;

import java.io.Serializable;
import java.util.ArrayList;

public class School implements Serializable {
    private Integer id;
    private String name;
    private String status;
    private Integer count;
    private ArrayList<String> yearDataList;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public ArrayList<String> getYearDataList() {
        return yearDataList;
    }

    public void setYearDataList(ArrayList<String> yearDataList) {
        this.yearDataList = yearDataList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", count=" + count +
                ", yearDataList=" + yearDataList +
                '}';
    }
}
