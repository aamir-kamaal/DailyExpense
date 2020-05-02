package com.example.aamir.dailyexpense;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.aamir.dailyexpense.calculations.DateCalculations;
import com.example.aamir.dailyexpense.customadapters.CategoryBaseAdapter;
import com.example.aamir.dailyexpense.database.DatabaseManager;
import com.example.aamir.dailyexpense.myobjects.CategoryItem;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExpenseInputFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExpenseInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseInputFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Edit text variables
    private EditText expenseName,expenseAmount,expenseDescription;
    private Button addButton,expenseDate;


    private DatabaseManager dbManager;
    private View fragView;
    private String expense_Name;
    private String expense_Amount;
    private String expense_Description;
    private String expense_date;
    private int mYear, mMonth, mDay;
    private InputMethodManager imm;
    private Context context;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Layout Inflater for category dialog
    public AlertDialog.Builder catBuilder;
    AlertDialog ad;
    LayoutInflater categoryLayout;
    GridView gv;
    View v;

    private OnFragmentInteractionListener mListener;


    // TODO: Rename and change types and number of parameters
    public static ExpenseInputFragment newInstance(String param1, String param2) {
        ExpenseInputFragment fragment = new ExpenseInputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        this.context = getActivity();
        dbManager = new DatabaseManager(getContext());
        dbManager.open();
        openCategoryDialog();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView = inflater.inflate(R.layout.fragment_expense_input, container, false);
        expenseName =  fragView.findViewById(R.id.expenseName);
        expenseAmount =  fragView.findViewById(R.id.expenseAmount);
        expenseDescription =  fragView.findViewById(R.id.expenseDesc);
        expenseDate =  fragView.findViewById(R.id.expenseDate);
        addButton =  fragView.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        expenseDate.setOnClickListener(this);
        ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Add New Expense");
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // CategoryItem cat = (CategoryItem) parent.getItemAtPosition(position);
               // Log.d("OBJECT OF CATEGORY ",cat.toString());
                expenseName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baby, 0);
                ad.dismiss();
                Toast.makeText(getActivity(),"Category Selected",Toast.LENGTH_SHORT).show();
            }
        });
        return fragView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).showFloatingActionButton();
    }

    @Override
    public void onClick(View v) {

        imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        if(v == addButton)
        {
            long rowsAffected = 0;
            expense_Name = expenseName.getText().toString();
            expense_Amount = expenseAmount.getText().toString();
            expense_Description = expenseDescription.getText().toString();
            if(expenseDate.getText().toString() == "") {
                expense_date = new DateCalculations().getToday();
            }
            else {
                expense_date = expenseDate.getText().toString();
            }
            Log.d("ACTION MSG","expense name is entered is" + expense_Name);
            Log.d("ACTION MSG","expense amount is entered is" + expense_Amount);
            Log.d("ACTION MSG","expense date is entered is" + expense_date);

            if (expense_Name.trim().equalsIgnoreCase(""))
            {
                expenseName.setError("Expense Name cannot be null");
                return;
            } else if (expense_Amount.trim().equalsIgnoreCase("")) {
                expenseAmount.setError("Expense Amount cannot be null");
            }
            rowsAffected = dbManager.insert("Others",expense_Name,expense_Amount,expense_Description,expense_date);
            if (rowsAffected > 0)
            {
                Toast.makeText(this.getActivity(),"Expense Added Successfully.",Toast.LENGTH_LONG).show();
            }
            expenseName.setText(null);
            expenseDescription.setText(null);
            expenseAmount.setText(null);
            expenseDate.setText("Date (Today)".toString());
        }
        if (v == expenseDate)
        {
            DatePicker();
        }
    }

    public void DatePicker()
    {
        final Calendar c  =  Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dpd = new DatePickerDialog(this.getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                expenseDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        },mYear,mMonth,mDay);
        dpd.show();
    }

    public void openCategoryDialog()
    {
        categoryLayout = this.getActivity().getLayoutInflater();
        v = categoryLayout.inflate(R.layout.category_grid_view,null);
        gv = (GridView) v.findViewById(R.id.categoryGridView);
        CategoryBaseAdapter  cba = new CategoryBaseAdapter(this.getActivity());
        gv.setAdapter(cba);
        catBuilder = new AlertDialog.Builder(this.getActivity());
        catBuilder.setTitle("Choose Category");
        catBuilder.setView(v);
       // catBuilder.setCancelable(false);
        ad = catBuilder.create();
        ad.show();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
