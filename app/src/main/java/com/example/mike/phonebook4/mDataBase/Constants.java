package com.example.mike.phonebook4.mDataBase;

/**
 * Created by Mike on 11/18/2016.
 */

public class Constants {
        //columns

    static final String ROW_ID="id";
    static final String ROW_ID2="id2";

    static final String NAME="name";
    static final String NUMBER="number";
    static final String USERNAME ="username";
    static  final String PASSWORD = "password";


    //properties
    static final String DB_NAME="hh_DB";
    static final String DB_NAME2="hh2_DB";

    static final String TB_NAME="hh_TB";
    static final String TB_NAME2="hh2_TB";

    static final int DB_VERSION=1;

    //creating tb
    static final String CREATE_TB="CREATE TABLE hh_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL," + "number TEXT NOT NULL );";
    static final String CREATE_TB2="CREATE TABLE hh2_TB(id2 INTEGER PRIMARY KEY AUTOINCREMENT,"
           + "username TEXT NOT NULL ," + "password TEXT NOT NULL);";


    //DROP TB
    static final String DROP_TB="DROP TABLE IF EXISTS " +TB_NAME;
    static final String DROP_TB2="DROP TABLE IF EXISTS " +TB_NAME2;



}
