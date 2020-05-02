package com.example.aamir.dailyexpense.calculations;

import com.example.aamir.dailyexpense.myobjects.Expense;
import com.example.aamir.dailyexpense.myobjects.ListOfExpenses;

import java.util.ArrayList;

public class ExpenseStats {

    private ArrayList<ListOfExpenses> expenseList;


    public ExpenseStats(){
    }

    public double sumOfExpense(ArrayList<ListOfExpenses> expenseList)
    {
        double sum = 0;
        for (ListOfExpenses e : expenseList) {

            for (Expense ee : e.getExpenseArrayList())
            {
                sum = sum + Double.parseDouble(ee.getExpenseAmount());
            }
        }
        return sum;

    }

    public double sumOfTodaysExpense(ArrayList<Expense> expenseList)
    {
        double sum = 0;
        for (Expense e : expenseList) {
            sum = sum + Double.parseDouble(e.getExpenseAmount());
        }
        return sum;

    }
}
