package com.example.aamir.dailyexpense.myobjects;

public class Expense {

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    private String expenseName;
    private String expenseDesc;
    private String expenseCat;
    private String expenseAmount;
    private String expenseDate;

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setExpenseAmount(String expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseDesc() {
        return expenseDesc;
    }

    public void setExpenseDesc(String expenseDesc) {
        this.expenseDesc = expenseDesc;
    }

    public String getExpenseCat() {
        return expenseCat;
    }

    public void setExpenseCat(String expenseCat) {
        this.expenseCat = expenseCat;
    }



}
