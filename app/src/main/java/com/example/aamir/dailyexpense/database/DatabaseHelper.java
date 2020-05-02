package com.example.aamir.dailyexpense.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {

//    Database Name
    static final String DB_NAME = "DAILY_EXPENSE_APP.DB";
//    Table Names
    public static final String  TABLE_EXPENSE_MASTER = "TABLE_EXPENSE_MASTER";
    public static final String  TABLE_EXPENSE_CATEGORGY = "TABLE_EXPENSE_CATEGORGY";
//    public static final String  TABLE_NAME = "";

//    Table Columns for expense master
    public static final String expense_id = "expense_id";
    public static final String expense_name = "expense_name";
    public static final String expense_amount = "expense_amount";
    public static final String expense_description = "expense_description";
    public static final String expense_category = "expense_category";
    public static final String expense_date = "expense_date";


//    Table Columns for expense Category


//    Database version
    static final int DB_VERSION = 1;

//    Creating table queries
private static final String SQL_CREATE_EXP_MASTER =
        "CREATE TABLE " + TABLE_EXPENSE_MASTER + " (" +
                expense_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                expense_name + " TEXT," +
                expense_amount + " TEXT," +
                expense_description + " TEXT," +
                expense_category + " TEXT," +
                expense_date + " DATE)";




    public DatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EXP_MASTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE_MASTER);
        onCreate(db);
    }
}
