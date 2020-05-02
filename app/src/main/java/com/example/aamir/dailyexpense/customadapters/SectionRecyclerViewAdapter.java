package com.example.aamir.dailyexpense.customadapters;


import android.util.Log;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.example.aamir.dailyexpense.R;
import com.example.aamir.dailyexpense.calculations.ExpenseStats;
import com.example.aamir.dailyexpense.myobjects.ListOfExpenses;
import com.example.aamir.dailyexpense.viewholders.ExpenseTitleInfoViewHolder;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<ExpenseTitleInfoViewHolder> {

    private Context context;
    private ArrayList<ListOfExpenses> expenseList;
    private static final int STATIC_CARD = 0;
    private static final int DYNAMIC_CARD = 1;

    public SectionRecyclerViewAdapter(Context context, ArrayList<ListOfExpenses> myListOfExpenses) {
        this.context = context;
        this.expenseList = myListOfExpenses;
    }

    @NonNull
    @Override
    public ExpenseTitleInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i==STATIC_CARD){
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dashboard_stats_layout,viewGroup,false);
            v.setTag("static");
            Log.d("LOGGER MSG","" +
                    "first card working");
            Log.d("INSIDE RECYCLER ADAPTER","VALUE OF POSITION IS " + i);

            ExpenseTitleInfoViewHolder myholder = new ExpenseTitleInfoViewHolder(v,expenseList);
            return myholder;
        }
        else{
            Log.d("INSIDE RECYCLER ADAPTER","VALUE OF POSITION IS " + i);

            View view = LayoutInflater.from(context).inflate(R.layout.expense_list_header_layout, viewGroup, false);
            view.setTag("dynamic");
            return new ExpenseTitleInfoViewHolder(view,expenseList);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseTitleInfoViewHolder expenseTitleInfoViewHolder, int position) {

        if (position != STATIC_CARD)
        {
            final ListOfExpenses loe = expenseList.get(position-1);
            expenseTitleInfoViewHolder.expenseDate.setText(loe.expenseSectionTitle());
            expenseTitleInfoViewHolder.totalForDay.setText("Expenses: " + loe.sumExpenseToday());
            expenseTitleInfoViewHolder.itemRecyclerView.setHasFixedSize(true);
            expenseTitleInfoViewHolder.itemRecyclerView.setNestedScrollingEnabled(false);
            LinearLayoutManager llm = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            expenseTitleInfoViewHolder.itemRecyclerView.setLayoutManager(llm);
            ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(context, loe.getExpenseArrayList());
            expenseTitleInfoViewHolder.itemRecyclerView.setAdapter(adapter);
        }

    }

    @Override
    public int getItemCount() {
        return expenseList.size()+1;
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == 0)
            return STATIC_CARD;
        else
            return DYNAMIC_CARD;
    }


}
