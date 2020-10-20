package com.zhongsheng.education.entiy;

import java.util.Date;

public class Order {
    private Integer id;
    private String order_number;
    private String goods_name;
    private String sNum;
    private String price;
    private Date create_time;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getsNum() {
        return sNum;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_number='" + order_number + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", sNum='" + sNum + '\'' +
                ", price='" + price + '\'' +
                ", create_time=" + create_time +
                ", status=" + status +
                '}';
    }
}
