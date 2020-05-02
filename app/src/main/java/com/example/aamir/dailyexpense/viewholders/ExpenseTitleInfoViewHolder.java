package com.example.aamir.dailyexpense.viewholders;


import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import com.example.aamir.dailyexpense.R;
import com.example.aamir.dailyexpense.calculations.ExpenseStats;
import com.example.aamir.dailyexpense.myobjects.ListOfExpenses;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ExpenseTitleInfoViewHolder extends RecyclerView.ViewHolder {

    public TextView expenseDate;
    public TextView totalForDay;
    public RecyclerView itemRecyclerView;
    public TextView sumExpense;
    public ArrayList<ListOfExpenses> loe;
    public ExpenseTitleInfoViewHolder(@NonNull View itemView, ArrayList<ListOfExpenses> expenseList) {
        super(itemView);
        this.loe = expenseList;

        if (itemView.getTag().toString().equalsIgnoreCase("dynamic"))
        {
            this.expenseDate =  itemView.findViewById(R.id.expenseCurrentDate);
            this.totalForDay =  itemView.findViewById(R.id.expenseCurrentTotal);
            itemRecyclerView =  itemView.findViewById(R.id.item_recycler_view);

            expenseDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
            totalForDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
        }
        else
        {
            sumExpense = itemView.findViewById(R.id.sumOfExpense1);
            sumExpense.setText(Double.toString(new ExpenseStats().sumOfExpense(loe)));
        }

    }
}
