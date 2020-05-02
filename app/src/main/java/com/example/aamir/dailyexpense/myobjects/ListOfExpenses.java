package com.example.aamir.dailyexpense.myobjects;

import java.util.ArrayList;
import java.util.List;


public class  ListOfExpenses{

    ArrayList<Expense> expenseList;
    String expenseSectionTitle;
    String sumToday;

    public ListOfExpenses(ArrayList<Expense> expenseList, String expenseSectionTitle,String sum) {
        this.expenseList = expenseList;
        this.expenseSectionTitle = expenseSectionTitle;
        this.sumToday = sum;
    }

    public ArrayList<Expense> getExpenseArrayList() {
        return expenseList;
    }

    public String expenseSectionTitle() {
        return expenseSectionTitle;
    }

    public String sumExpenseToday() {
        return sumToday;
    }
}
