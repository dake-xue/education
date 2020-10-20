package com.zhongsheng.education.entiy;

public class PermissionInfo {

    private Integer id;
    private String pername;
    private String resource;
    private String sn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }


    @Override
    public String toString() {
        return "PermissionInfo{" +
                "id=" + id +
                ", pername='" + pername + '\'' +
                ", resource='" + resource + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}
