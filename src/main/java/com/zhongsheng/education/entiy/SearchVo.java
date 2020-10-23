package com.zhongsheng.education.entiy;

public class SearchVo {
    private Integer modules;
    private String keyword;
    private Integer status;
    private Integer area;

    public Integer getModules() {
        return modules;
    }

    public void setModules(Integer modules) {
        this.modules = modules;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "SearchVo{" +
                "modules=" + modules +
                ", keyword='" + keyword + '\'' +
                ", status=" + status +
                ", area=" + area +
                '}';
    }
}
