package com.zhongsheng.education.entiy;

public class User {
    private int uid;
    private int username;
    private String password;
    private int whoid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWhoid() {
        return whoid;
    }

    public void setWhoid(int whoid) {
        this.whoid = whoid;
    }

    @Override
    public String toString() {
        return "user{" +
                "uid=" + uid +
                ", username=" + username +
                ", password='" + password + '\'' +
                ", whoid=" + whoid +
                '}';
    }
}
