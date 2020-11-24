package com.example.wifianalyzer;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private Context context;
    private SQLiteDatabase database;
    private SampleSQLiteDBHelper dbHelper;

    public DBManager(Context c) {
        this.context = c;
    }

    public com.example.wifianalyzer.DBManager open() throws SQLException {
        this.dbHelper = new SampleSQLiteDBHelper(this.context);
        this.database = this.dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.dbHelper.close();
    }







}