package com.zhongsheng.education.entiy;

import java.io.Serializable;

public class Bill implements Serializable {

    private int bid;
    private String snum;
    private int paymentAmount;
    private String image;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "bid=" + bid +
                ", snum='" + snum + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", image='" + image + '\'' +
                '}';
    }
}
