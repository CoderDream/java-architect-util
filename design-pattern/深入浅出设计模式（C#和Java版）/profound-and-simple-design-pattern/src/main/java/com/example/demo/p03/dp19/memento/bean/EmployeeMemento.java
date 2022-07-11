package com.example.demo.p03.dp19.memento.bean;

public class EmployeeMemento {
    public int mId;
    public String mFirstName;
    public String mLastName;
    public int mSalary;

    public EmployeeMemento(int pId, String pFirstName, String pLastName, int pSalary) {
        mId = pId;
        mFirstName = pFirstName;
        mLastName = pLastName;
        mSalary = pSalary;
    }
}
