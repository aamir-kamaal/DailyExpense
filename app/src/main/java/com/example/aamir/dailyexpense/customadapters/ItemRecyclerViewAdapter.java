package com.example.aamir.dailyexpense.customadapters;


import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.example.aamir.dailyexpense.R;
import com.example.aamir.dailyexpense.myobjects.Expense;
import java.util.ArrayList;
import com.example.aamir.dailyexpense.viewholders.ExpenseListViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ExpenseListViewHolder> {

    private Context context;
    private ArrayList<Expense> arrayListExpense;

    public ItemRecyclerViewAdapter(Context context, ArrayList<Expense> arrayList) {
        this.context = context;
        this.arrayListExpense = arrayList;
    }


    @NonNull
    @Override
    public ExpenseListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_expense_item, viewGroup, false);
        return new ExpenseListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseListViewHolder expenseListViewHolder, int position) {
        expenseListViewHolder.expenseName.setText(arrayListExpense.get(position).getExpenseName());
        expenseListViewHolder.expenseAmount.setText(arrayListExpense.get(position).getExpenseAmount());
        expenseListViewHolder.expenseSymbol.setImageResource(R.drawable.fuel);



    }

    @Override
    public int getItemCount() {
        return arrayListExpense.size();
    }
}
