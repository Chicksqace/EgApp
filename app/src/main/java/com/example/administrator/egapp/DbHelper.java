package com.example.administrator.egapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2022/9/30.
 */

public class DbHelper extends SQLiteOpenHelper {
    //定义数据库名
    private static final String DATABASE_NAME = "EGAPP";

    //定义构造函数，设置数据库初始化参数
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    //数据库创建时会执行
    @Override
    public void onCreate(SQLiteDatabase db) {

        //创建USER表
        String createSql = "CREATE TABLE USER(ID INTEGER PRIMARY KEY AUTOINCREMENT,USER TEXT NOT NULL UNIQUE,PASSWORD TEXT NOT NULL)";
        db.execSQL(createSql);

    }

    //数据库升级时会执行
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    // 插入用户到数据库
    public long insertUser(String username, String password){
        //获得一个可写操作的数据库
        SQLiteDatabase writableDatabase = this.getWritableDatabase();

        //将插入参数封装到ContentValues中，利用db中的insert函数，将相关数据插入到数据库中。
        //这里有两种方式，一种是直接通过输写sql语句插入，但是需要拼接字符串等操作，容易写错。
        //因此，这里采用的第二种方式，这种方式通过contentValues对我们的参数进行了封装
        // ，使得能够在不书写sql语句的情况下也能够实现
        //数据库的插入。
        ContentValues contentValues = new ContentValues();
        contentValues.put("user",username);
        contentValues.put("password",password);
        //返回插入成功的数量
        long insert = writableDatabase.insert("user", null, contentValues);
        writableDatabase.close();
        return insert;
    }


    public boolean selectUser(String username,String password){
        String searchSql = "SELECT * FROM USER WHERE USER=? AND PASSWORD=?";
        //替换占位符里面的？，利用字符串数组进行替换
        String[] values = new String[]{username,password};

        //获取可读的数据库
        SQLiteDatabase readableDatabase = this.getReadableDatabase();

        //得到查询的数据，返回一个cursor 游标
        Cursor cursor = readableDatabase.rawQuery(searchSql, values);
        //如果能查询的数据大于0，则代表至少有一个数据。
        if (cursor.getCount()>0){
            //释放cursor资源
            cursor.close();
            readableDatabase.close();
            return true;
        }
        //释放cursor资源。
        cursor.close();
        readableDatabase.close();
        return false;
    }
}
