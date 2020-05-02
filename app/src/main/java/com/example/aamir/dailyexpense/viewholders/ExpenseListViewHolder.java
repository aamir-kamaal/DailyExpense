package com.example.aamir.dailyexpense.viewholders;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aamir.dailyexpense.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExpenseListViewHolder extends RecyclerView.ViewHolder {

    public ImageView expenseSymbol;
    public TextView expenseName;
    public TextView expenseAmount;

    public ExpenseListViewHolder(@NonNull View itemView) {
        super(itemView);
        this.expenseSymbol = itemView.findViewById(R.id.expenseSymbol);
        this.expenseName = itemView.findViewById(R.id.expenseNameView);
        this.expenseAmount = itemView.findViewById(R.id.expenseAmountView);
    }
}
