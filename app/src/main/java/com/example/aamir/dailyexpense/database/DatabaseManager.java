package com.example.aamir.dailyexpense.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.DateFormat;
import android.database.Cursor;
import android.util.Log;

import java.time.LocalDateTime;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c){

        context = c;
    }

    public DatabaseManager open() throws SQLException
    {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public long insert(String expCat,String name,String amount,String desc,String expDate)
    {
        Cursor c = null;
        long affectedRowCount = 0;
       // Date date = Calendar.getInstance().getTime();
       // DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
       // String strDate = dateFormat.format(date);
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.expense_name,name);
        contentValue.put(DatabaseHelper.expense_amount,amount);
        contentValue.put(DatabaseHelper.expense_description,desc);
        contentValue.put(DatabaseHelper.expense_category,expCat);
        contentValue.put(DatabaseHelper.expense_date,expDate);
        database.insert(DatabaseHelper.TABLE_EXPENSE_MASTER,null,contentValue);
        c = database.rawQuery("select changes() as affected_rows",null);
        if(c.getCount()>0 && c != null && c.moveToFirst())
        {
            affectedRowCount = c.getLong(c.getColumnIndex("affected_rows"));
            Log.d("LOG", "affectedRowCount = " + affectedRowCount);
        }
        else
        {
            Log.d("ERROR", "Exception has occurred in inserting rows");
        }
        Log.d("Inside Database","Table insertion completed successfully");
        return affectedRowCount;
    }

    public Cursor fetch()
    {
        String[] columns = new String[]{
                DatabaseHelper.expense_category,DatabaseHelper.expense_name,
                DatabaseHelper.expense_amount,DatabaseHelper.expense_description,DatabaseHelper.expense_date};

        Cursor cursor = database.query(DatabaseHelper.TABLE_EXPENSE_MASTER,columns,null,
                null,null,null,DatabaseHelper.expense_date);

        if (cursor != null)
        {
            cursor.moveToFirst();
            Log.d("LOGGER MSG","Getting this ");
        }

        return cursor;
    }

}
