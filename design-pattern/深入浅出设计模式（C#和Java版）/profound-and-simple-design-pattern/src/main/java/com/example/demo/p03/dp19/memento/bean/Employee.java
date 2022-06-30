package com.example.demo.p03.dp19.memento.bean;

public class Employee {
    private int mId = 0;
    private String mFirstName = "";
    private String mLastName = "";
    private int mSalary = 0;

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String pFirstName) {
        mFirstName = pFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String pLastName) {
        mLastName = pLastName;
    }

    public int getSalary() {
        return mSalary;
    }

    public void setSalary(int pSalary) {
        mSalary = pSalary;
    }

    //生成保存其状态的memento对象
    public EmployeeMemento getMemento() {
        return new EmployeeMemento(mId, mFirstName, mLastName, mSalary);
    }

    //将memento对象的状态返回给自已
    public void setMemento(EmployeeMemento m) {
        mId = m.mId;
        mFirstName = m.mFirstName;
        mLastName = m.mLastName;
        mSalary = m.mSalary;
    }
}
