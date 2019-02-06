package com.alphaindelhi.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class Databasehelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "Mystudent.db";
    public final static String TABLE_NAME = "mystudent_table";
    public final static String COL_1 = "ID";
    public final static String COL_2 = "NAME";
    public final static String COL_3 = "ADDRESS";
    public final static String COL_4 = "EMAIL";
    public final static String COL_5 = "MOBILE_NO";


    public Databasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT ," +
                " ADDRESS TEXT ," +
                " EMAIL TEXT ," +
                "MOBILE_NO INTEGER) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);



    }

    public boolean insertData(String name , String address, String email, String mobileNo)
    {
        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 , name);
        contentValues.put(COL_3 , address);
        contentValues.put(COL_4 , email);
        contentValues.put(COL_5 , mobileNo);

        long result = sdb.insert(TABLE_NAME , null , contentValues);

        if(result==-1)
        {
            return false;
        } else

        {return true;}


    }



    public Cursor getAllData(){

        SQLiteDatabase sdb = this.getWritableDatabase();

        Cursor cursor = sdb.rawQuery("SELECT * FROM "+TABLE_NAME, null) ;
        return cursor;

    }










}
