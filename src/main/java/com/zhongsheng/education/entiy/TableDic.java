package com.zhongsheng.education.entiy;

public class TableDic {

    private String tableName;
    private Integer id;
    private String name;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

    @Override
    public String toString() {
        return "Table_dc{" +
                "tableName='" + tableName + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
