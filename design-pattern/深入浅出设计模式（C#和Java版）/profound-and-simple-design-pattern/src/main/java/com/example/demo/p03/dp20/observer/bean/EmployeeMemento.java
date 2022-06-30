package com.example.demo.p03.dp20.observer.bean;

public class EmployeeMemento {
    int mId;
    String mFirstName;
    String mLastName;
    int mSalary;

    EmployeeMemento(int pId, String pFirstName, String pLastName, int pSalary) {
        mId = pId;
        mFirstName = pFirstName;
        mLastName = pLastName;
        mSalary = pSalary;
    }
}
