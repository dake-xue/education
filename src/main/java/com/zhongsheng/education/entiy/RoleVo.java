package com.zhongsheng.education.entiy;

public class RoleVo {

    private String name;
    private String pername;
    private String sn;
    private String resource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "RoleVo{" +
                "name='" + name + '\'' +
                ", pername='" + pername + '\'' +
                ", sn='" + sn + '\'' +
                ", resource='" + resource + '\'' +
                '}';
    }
}
