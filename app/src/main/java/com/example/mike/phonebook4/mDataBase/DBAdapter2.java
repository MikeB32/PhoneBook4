package com.example.mike.phonebook4.mDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by Mike on 12/6/2016.
 */

public class DBAdapter2 {



    public native String stringFromJNI();

    static {
        System.loadLibrary("native-lib");
    }

    public String Pass = stringFromJNI();


    Context c;
    SQLiteDatabase db;
    DBHelper2 helper;

    public DBAdapter2(Context c) {
        this.c = c;
        helper = new DBHelper2(c);
    }


    public void openDB2() {
        try {
            db = helper.getWritableDatabase(Pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB2() {
        try {
            helper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //REGISTER
    public boolean register(String username, String password) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.USERNAME, username);
            cv.put(Constants.PASSWORD, password);
            long result = db.insert(Constants.TB_NAME2, Constants.ROW_ID2, cv);
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query(Constants.TB_NAME2, null, Constants.USERNAME +"=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(Constants.PASSWORD));
        cursor.close();
        return password;
    }

}

