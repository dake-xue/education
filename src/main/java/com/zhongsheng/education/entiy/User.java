package com.zhongsheng.education.entiy;

public class User {

    private int uid;
    private String name;
    private String username;
    private String password;
    private int whoid;
    private int status;



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", username=" + username +
                ", password='" + password + '\'' +
                ", whoid=" + whoid +
                ", status=" + status +
                '}';
    }
}
