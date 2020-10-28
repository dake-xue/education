package com.zhongsheng.education.entiy;

import java.io.Serializable;
import java.util.ArrayList;

public class Statistics implements Serializable {
    private ArrayList<String> peopleCount;
    private ArrayList<String> moneyCount;
    private Integer people;
    private Integer money;

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public ArrayList<String> getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(ArrayList<String> peopleCount) {
        this.peopleCount = peopleCount;
    }

    public ArrayList<String> getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(ArrayList<String> moneyCount) {
        this.moneyCount = moneyCount;
    }
}
