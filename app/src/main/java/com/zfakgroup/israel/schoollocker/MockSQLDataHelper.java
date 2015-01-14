package com.zfakgroup.israel.schoollocker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mac on 14.01.15.
 *
 * SQL - helper - заглушка. Пока что будем выполнять запросы к локальной БД.
 *
 */
public class MockSQLDataHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE = "CREATE TABLE myTable(...)";
    final static String DB_NAME = "mySuperDB.db";
    Context mContext;

    public MockSQLDataHelper(Context context, int dbVer){
        super(context, DB_NAME, null, dbVer);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
