package com.example.mike.phonebook4.mDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.example.mike.phonebook4.EncDec;

import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by Mike on 11/18/2016.
 */


public class DBAdapter {



    public native String stringFromJNI();

    static {
        System.loadLibrary("native-lib");
    }

    public String Pass = stringFromJNI();


    Context c;
    SQLiteDatabase db;
    DBHelper helper;
    EncDec encDec;

    public DBAdapter(Context c) {
        this.c = c;
        helper = new DBHelper(c);
    }


    public void openDB() {
        try {
            db = helper.getWritableDatabase(Pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            helper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //save
    public boolean add(String name, String number) {
        try {


            ContentValues cv = new ContentValues();
            cv.put(Constants.NAME, name);
            cv.put(Constants.NUMBER, number);
            long result = db.insert(Constants.TB_NAME, Constants.ROW_ID, cv);
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    //Select
    public Cursor retrieve()  {

        String[] columns = {Constants.ROW_ID, Constants.NAME, Constants.NUMBER};
        Cursor c = db.query(Constants.TB_NAME, columns, null, null, null, null, null);
        return c;
    }

    //Update/edit
    public boolean update(String newName, String newNumber, int id) {

        try {
            encDec = new EncDec();
            String a =encDec.encrypt(newName);
            String b =encDec.encrypt(newNumber);
            ContentValues ab = new ContentValues();
            ab.put(Constants.NAME, a);
            ab.put(Constants.NUMBER, b);
            int result = db.update(Constants.TB_NAME, ab, Constants.ROW_ID + " = ?", new String[]{String.valueOf(id)});
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    //Delete///Reomve

    public boolean delete(int id) {
        try {
            int result = db.delete(Constants.TB_NAME, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;

    }
}
