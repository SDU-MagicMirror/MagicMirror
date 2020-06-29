package com.qilu.core.util.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class UserCollectionHelper extends SQLiteOpenHelper {
    private static final String TAG = "UserCollectionHelper";
    //数据库建表语句
    private static final String sql_create = "create table UserCollection (id text primary key, content text, image text)";
    public UserCollectionHelper(@Nullable Context context) {
        super(context, "UserCollection", null, 1);//创建数据库调用方法
    }
    /**
     * 第一次创建数据库时调用
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate: " );
        db.execSQL(sql_create);
    }
    /**
     * 版本更新的时候调用
     * 此方法暂时废弃
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
