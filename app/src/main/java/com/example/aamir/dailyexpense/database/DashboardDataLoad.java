package com.example.aamir.dailyexpense.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.aamir.dailyexpense.calculations.DateCalculations;
import com.example.aamir.dailyexpense.calculations.ExpenseStats;
import com.example.aamir.dailyexpense.myobjects.Expense;
import com.example.aamir.dailyexpense.myobjects.ListOfExpenses;

import java.util.ArrayList;
import java.util.Arrays;

public class DashboardDataLoad {

    private DatabaseHelper dbHelper;
    private DatabaseManager myDbManager;
    private ArrayList<Expense> myExpenseList;
    private ArrayList<ListOfExpenses> myListOfExpense;
    private Context context;
    private int debug_flag;

    public DashboardDataLoad(Context context) {
        this.context = context;
    }

    public ArrayList<ListOfExpenses> dashboardDataList() throws Exception {
        myDbManager = new DatabaseManager(context);
        myDbManager.open();
        Cursor c = myDbManager.fetch();
        Expense singleExpense;
        dbHelper = new DatabaseHelper(context);
        myExpenseList = new ArrayList<>();
        Log.d("COUNT OF CURSOR", String.valueOf(c.getCount()));
        // String finalDate = c.getString(c.getColumnIndex(dbHelper.expense_date));

        // Log.d("LOGGER MSG","Current expense date initialized is " + date);
        if (c.getCount() > 0) {
            do {
                singleExpense = new Expense();
                singleExpense.setExpenseName(c.getString(c.getColumnIndex(dbHelper.expense_name)));
                singleExpense.setExpenseName(c.getString(c.getColumnIndex(dbHelper.expense_name)));
                singleExpense.setExpenseAmount(c.getString(c.getColumnIndex(dbHelper.expense_amount)));
                singleExpense.setExpenseCat(c.getString(c.getColumnIndex(dbHelper.expense_category)));
                singleExpense.setExpenseDesc(c.getString(c.getColumnIndex(dbHelper.expense_description)));
                singleExpense.setExpenseDate(c.getString(c.getColumnIndex(dbHelper.expense_date)));
                myExpenseList.add(singleExpense);
                //date = c.getString(c.getColumnIndex(dbHelper.expense_date));
                debug_flag = 1;
                //Log.d("LOGGER MSG","Current expense date initialized in loop is " + date);
                debug_flag = 1;
                Log.d("LOGGER MSG", "Single expense added for " + singleExpense.getExpenseName());
              //  Log.d("LOGGER MSG", "Current expense date captured is " + singleExpense.getExpenseDate());
            } while (c.moveToNext());
            debug_flag = 6;
        }
        c.close();
        myDbManager.close();

        //Adding code for segregation of Expense item according to date

        myListOfExpense = new ArrayList<>();
        ArrayList<Expense> segregateList = new ArrayList<>();
        String current_date;
        String currDateInformat = null;

        for (int i = 0; i < getdaysInExpenseList().size(); i++) {
            current_date = String.valueOf(getdaysInExpenseList().get(i));
            for (Expense e : myExpenseList) {
                Log.d("LOGGER MSG","Length of no of days of expenses " + getdaysInExpenseList().size());

                if (current_date.equalsIgnoreCase(removeLeadingZeroes(new DateCalculations().getDateString(e.getExpenseDate())))){
                    segregateList.add(e);
                currDateInformat = e.getExpenseDate();}
            }
            ExpenseStats es = new ExpenseStats();
            Double sumToday = es.sumOfTodaysExpense(segregateList);
            myListOfExpense.add(new ListOfExpenses(segregateList, new DateCalculations().getFormattedDate(currDateInformat), sumToday.toString()));
            segregateList = new ArrayList<>();
        }

        Log.d("LOGGER MSG", "Current Size of expense arraylist " + myExpenseList.size() +
                " and Size of MasterList " + myListOfExpense.size());
        return myListOfExpense;

    }

    public int getDaysOfMonth(String month) {

        String months[] = {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October",
                "November", "December"};

        int DaysinMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return DaysinMonth[Arrays.asList(months).indexOf(month)];
    }

    // Function to return number of days of month on which expenses have been added.
    public ArrayList<Integer> getdaysInExpenseList() {
        ArrayList<Integer> days = new ArrayList<>();
        int index = 0;
        String date;
        date = new DateCalculations().getDateString(myExpenseList.get(0).getExpenseDate());
        days.add(Integer.parseInt(date));
        for (Expense e : myExpenseList) {
            date = new DateCalculations().getDateString(e.getExpenseDate());
            if (Integer.parseInt(date) == days.get(index)) {
                continue;
            } else {
                index +=1;
                days.add(Integer.parseInt(date));
            }
        }
        return  days;
    }

    public static String removeLeadingZeroes(String value) {
        while (value.length() > 1 && value.indexOf("0")==0)
            value = value.substring(1);
        return value;
    }

}
