package com.example.aamir.dailyexpense;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.aamir.dailyexpense.customadapters.SectionRecyclerViewAdapter;
import com.example.aamir.dailyexpense.database.DashboardDataLoad;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    SectionRecyclerViewAdapter srva;
    int debug_flag = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.dashboard_fragment,container,false);
        ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
        ab.setTitle(null);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        recyclerView = rootView.findViewById(R.id.mainRecycler);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        debug_flag = 3;
        try
        {
            DashboardDataLoad load = new DashboardDataLoad(getActivity());
            srva = new SectionRecyclerViewAdapter(getActivity(),load.dashboardDataList());
            /*if(srva.getItemCount() == 0)
            {
            Log.d("ADAPTER EMPTY","No data in recycler view");

                LinearLayout defaultmsg =  rootView.findViewById(R.id.dashboardLinearLayout);
               *//* LayoutParams lparams = new LayoutParams(
                        ViewGroup.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
               *//* TextView tv =new TextView(getActivity());
                tv.setText("No Expenses added yet!");
                defaultmsg.addView(tv);
            }*/
            recyclerView.setAdapter(srva);
            Log.d("LOGGER MSG","Adapter set for recycler view");
        }
        catch (CursorIndexOutOfBoundsException cc)
        {
            Log.d("ADAPTER EMPTY","No data in recycler view");
            LinearLayout defaultmsg =  rootView.findViewById(R.id.dashboardLinearLayout);
               /* LayoutParams lparams = new LayoutParams(
                        ViewGroup.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
               */ TextView tv =new TextView(getActivity());
            tv.setText("No Expenses added yet!");
            defaultmsg.addView(tv);
        }
        catch (Exception e)
        {

            Log.d("EXCEPTION MSG AT : " + debug_flag,"Exception happened " + e.toString());
        }

        return rootView;
    }
}
