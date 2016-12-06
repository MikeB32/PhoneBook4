package com.example.mike.phonebook4.mDataBase;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * Created by Mike on 11/18/2016.
 */

public class DBHelper  extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL(Constants.CREATE_TB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL(Constants.DROP_TB);
        onCreate(db);
    }
}
