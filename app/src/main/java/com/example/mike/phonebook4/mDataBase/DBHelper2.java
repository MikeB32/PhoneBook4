package com.example.mike.phonebook4.mDataBase;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * Created by Mike on 12/6/2016.
 */

public class DBHelper2  extends SQLiteOpenHelper {
    public DBHelper2(Context context) {
        super(context, Constants.DB_NAME2, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TB2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(Constants.DROP_TB2);
        onCreate(db);
    }
}
